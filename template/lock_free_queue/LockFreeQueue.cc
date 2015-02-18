#include "LockFreeQueue.h"
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <assert.h>
#include <set>

#define NUM_THREADS 10000

/**
 * Lock Free Queue constructor
 * @param max_blocks valid index of the queue (size of the queue - 1)
 *
 */
Lock_free_queue::Lock_free_queue(unsigned long max_blocks)
{
  max_blocks_possible = max_blocks;
  if(max_blocks != 0)
    free_memory_queue = new Entry[max_blocks];                           
  else  
    free_memory_queue = 0;

  front = 0;
  back = 0;
}


/**
 * Get the block from the queue
 * @param block pointer to the desired block
 * @return false if there is none, otherwise return true
 *
 */
bool Lock_free_queue::get_new_block(unsigned int **block)
{
  if(max_blocks_possible == 0)
  {
    return 0;
  }
  unsigned long front_copy;
  unsigned long front_copy_index;
  int64_t version_copy, expected_version;

  assert(front <= back && (front + max_blocks_possible) >= back);

  while(1)
  {
    front_copy = front;
    front_copy_index = front_copy % max_blocks_possible;
    version_copy = free_memory_queue[front_copy_index].version;
    if(version_copy == -1)                             
    {                                                
      return 0;
    }   

    expected_version = front_copy/max_blocks_possible;

    if(version_copy != expected_version)            
    {   
      return 0;
    }   

    expected_version = front_copy/max_blocks_possible;

    if(version_copy != expected_version)            
    {   
      return 0;
    }

    if(__sync_bool_compare_and_swap(&front, front_copy, front_copy + 1))
      break;
  }

  __sync_lock_test_and_set(block, free_memory_queue[front_copy_index].block);
  __sync_lock_test_and_set(&free_memory_queue[front_copy_index].version, -1);

  return 1;
}

/**
 * Reserve and append a block to the queue
 * @param block block to be added to the queue
 * @return false if the queue is full, otherwise return true
 *
 */
bool Lock_free_queue::append_block(unsigned int *block)
{
  if(max_blocks_possible == 0)
  {
    return 0;
  }
  unsigned long back_copy;
  
  assert(front <= back && (front + max_blocks_possible) >= back);
  do
  {
    back_copy = back;
    if(back_copy + 1 > front + max_blocks_possible)
    {
      return 0;
    }
  } while(!__sync_bool_compare_and_swap(&back, back_copy, back_copy + 1));


  unsigned long back_copy_index = back_copy % max_blocks_possible;
  while(free_memory_queue[back_copy_index].version != -1)
  {
    pthread_yield();
  }
  __sync_lock_test_and_set(&free_memory_queue[back_copy_index].block, block);
  __sync_lock_test_and_set(&free_memory_queue[back_copy_index].version, back_copy/max_blocks_possible);
  return 1;
}

pthread_mutex_t mutex;
unsigned int i = 0;
std::set<unsigned int> ret_set;

void *produce(void *arg){
  usleep(rand() % 100);
  Lock_free_queue *queue = (Lock_free_queue *) arg;
  unsigned int *word_ptr = (unsigned int *) malloc(sizeof(unsigned int));
  pthread_mutex_lock(&mutex);
  *word_ptr = i++;
  pthread_mutex_unlock(&mutex);

  if(queue->append_block(word_ptr)){
    printf("append block %d\n", *word_ptr);
  }
  pthread_exit(0);
}

void *consume(void *arg){
  usleep(rand() % 100);
  Lock_free_queue *queue = (Lock_free_queue *) arg;
  unsigned int *word = (unsigned int *)malloc(sizeof(unsigned int));
  if(queue->get_new_block(&word)){
    unsigned int ret = (unsigned int) *word;
    printf("get new block %d\n", ret);
    pthread_mutex_lock(&mutex);
    if(ret_set.find(ret) == ret_set.end()){
      ret_set.insert(ret);
    }else{
      printf("get duplicate words %d\n", ret);
      assert(false);
    }
    pthread_mutex_unlock(&mutex);
  }
  pthread_exit(0);
}

int main(){
  Lock_free_queue *block_queues = new Lock_free_queue(20);
  pthread_mutex_init(&mutex, 0);
  pthread_t threads[NUM_THREADS];
  int rc;
  for(int i = 0; i < NUM_THREADS; i++){
    int j = rand() % 10;
    if(j < 5){
      rc = pthread_create(&threads[i], 0, produce, (void *)block_queues);
    }else{
      rc = pthread_create(&threads[i], 0, consume, (void *)block_queues);
    }
    if(rc){
      printf("ERROR: return code from pthread_create is %d\n", rc);
      exit(-1);
    }
  }
  pthread_exit(0);
}

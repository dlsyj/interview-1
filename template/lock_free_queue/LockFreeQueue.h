#include<stdint.h>
class Lock_free_queue
{
public:
  struct Entry
  {
    Entry()
    {   
      block = 0;
      version = -1; 
    };  

    volatile unsigned int *block;
    volatile int64_t version;
  };  

  volatile unsigned long front;
  volatile unsigned long back;
  unsigned long max_blocks_possible;
  Entry *free_memory_queue;

public:
  Lock_free_queue(unsigned long max_blocks);
  bool get_new_block(unsigned int **block);
  bool append_block(unsigned int *block);
};

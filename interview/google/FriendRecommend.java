import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * 社交网络中，如何实现好友推荐。抽象成图，然后找出共同好友最多的那个人
 */

public class FriendRecommend{
  public class User{
    public String name;
    public List<User> friends;
    public User(String name){
      this.name = name;
      this.friends = new ArrayList<User>();

    }

    public void addFriend(User user){
      this.friends.add(user);
    }

    public boolean isFriend(User user){
      return this.friends.contains(user);
    }

  }

  public static User recommend(User user){
    if(null == user){
      throw new IllegalArgumentException("user is null");
    }
    List<User> friends = user.friends;
    Map<User, Integer> commonFriendsNum = new HashMap<User, Integer>();
    for(User u : friends){
      for(User fri : u.friends){
        if(fri != user && user.isFriend(fri)){
          if(commonFriendsNum.contains(fri)){
            commonFriendsNum.put(fri, commonFriendsNum.get(fri) + 1);
          }else{
            commonFriendsNum.put(fri, 1);
          }
        }
      }
    }

    User ret = null;
    int max = Integer.MIN_VALUE;
    for(User u : commonFriendsNum.keySet()){
      if(commonFriendsNum.get(u) > max){
        ret = u;
        max = commonFriendsNum.get(u);
      }
    }
    return ret;
  }

}

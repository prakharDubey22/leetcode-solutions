class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
      
      // initialize maximum, minimum and ans with the first element
        int maxi = nums[0];
        int mini = nums[0];
        int ans = nums[0];
      
      //traverse from the second element
        for(int i = 1; i<n; i++) {
          
          //if curr number is negative swap maxi and mini
            if(nums[i] < 0) {
                int temp = maxi;
                maxi = mini;
                mini = temp;
            }
          
          //updating maximum and minimum products ending at current index
            mini = Math.min(mini * nums[i], nums[i]);
            maxi = Math.max(maxi * nums[i], nums[i]);
          
          // update the answer
            ans  = Math.max(ans, maxi);
        }
        return ans;
        
    }
}

import java.util.Arrays;

public class TestQ2 {

    public static void main(String[] args) {

        int[] nums = new int[] {1,2,3,4,5};

        System.out.println(Arrays.stream(nums).max().getAsInt());

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++){
            max = Math.max(nums[i], max);
        }

        System.out.println(max);
    }
}

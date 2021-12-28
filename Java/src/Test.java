public class Test {

public void thirdTask(int length)
{
    for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(length - i + 1);
            }
            System.out.println();
        }
}
public void fourthTask(int length)
{
        for (int i = 1; i <= length; i++) {
            for (int j = 4; j > i; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= i ; k++ ){
                System.out.print("*");
            }
            System.out.println(" ");
        }
}
public void fitthTask(int[] arr,int n)
{
    int a = arr[0],b = arr[1];
    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++) {
            if (arr[i] * arr[j]>a*b)
            {
                a=arr[i];
                b=arr[j];
            }
        }   
    }
    System.out.println("Max of numb" + a +" "+ b);
}
    public static void main(String[] args) {
    Test test = new Test();
        //test.thirdTask(5);
         //test.fourthTask(4);

        int[] arr = {-1,-4,-3,2,0,-5};
        int length = arr.length;
        test.fitthTask(arr,length);
    }
}

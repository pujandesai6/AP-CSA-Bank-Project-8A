public class ASCII
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 12; i++ )
        {
            for (int v = 0; v < 28; v++)
            {
                if (i > 0 && (v == 23 - i || v == i - 1))
                {
                    System.out.print("$$");
                    continue;
                }
                System.out.print(" ");
            }
            for (int c = 1; c < 22; c++)
            {
                if (i > 0 && (i == 1 || i == 11) && c <= 16)
                {
                    System.out.print("$");
                }
                else
                {
                    System.out.print("");
                }
                if (c == 1 && i != 0)
                {
                	System.out.print("$$");
                }
            }
            System.out.println();
        }
    }
}

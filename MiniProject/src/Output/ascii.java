package Output;


    public class ascii {
        public static void main(String[] args) {
            String risk1 = "GitHub Frenzy";//get riskName
            String risk2 = "Slack Overload";
            String risk3 = "Trello Confusion";
            double riskLevel = 1.5;

            double i = 0;
            String title = "RISK MATRIX";
            // Left-justify this title to 20 characters.
            String padded = String.format("%1$-20s", title);
            String asterisk = "*";
            String padRisk1 = String.format("%1$-20s", risk1);
            String padRisk2 = String.format("%1$-20s", risk2);
            String padRisk3 = String.format("%1$-20s", risk3);
            // Our result.
            System.out.print("|");
            System.out.print(padded);
            System.out.println("|");
            System.out.println(". . . . . . . . . . . . . .");

            System.out.print(padRisk1);
            printAsterix(i,riskLevel,asterisk);

            System.out.print("\n" + padRisk2);
            printAsterix(i,riskLevel,asterisk);

            riskLevel = 3.5;
            System.out.print("\n" + padRisk3);
            printAsterix(i,riskLevel,asterisk);

        }

            private static void printAsterix ( double i, double riskLevel, String asterisk){
                for (i = 0; i < riskLevel; i = i + 0.5) {
                    System.out.print(asterisk);

                }
            }
        }





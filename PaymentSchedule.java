import java.util.ArrayList;

public class PaymentSchedule 
{
    ArrayList<String> methods = new ArrayList<String>();//lista que contem todos os metodos
    
    PaymentSchedule()//construtor com as 3 primeiras agendar
    {
        String aux = "Semanal 1 FRIDAY";
        this.methods.add(aux);

        aux = "Mensal $";
        this.methods.add(aux);

        aux = "Semanal 2 FRIDAY";
        this.methods.add(aux);
    }

    public void print()//funcao que uso pra apresentar as opcoes
    {
        for(int i = 0;i < methods.size();i++)
        {
            String aux = methods.get(i);

            System.out.printf("[%d] - %s\n",i,aux);
        }
    }

    public void CriarNovaAgenda()
    {
        Main.clear();
        String nova;
        int option;

        System.out.print("Escolha a opção Semanal[1] ou Mensal[2]: ");//primeiro escolhe o tipo
     
        while(true)
        {
            option = Validar.int_valido();

            if(option == 1 || option == 2)
            {
                break;
            }

            System.out.print("Opção ínvalida\n");
        }

        if(option == 1)
        {
            nova = "Semanal";
            int aux;
            
            System.out.print("Indique a cada quantas semanas ocorre o pagamento: ");
            while(true)
            {
                aux = Validar.int_valido();

                if(aux >= 1 && aux <= 4)
                {   
                    String str = Integer.toString(aux);
                    nova += " ";
                    nova += str;
                    nova += " ";
                    System.out.println(nova);    
                    Main.clear();
                    break;
                }

                System.out.println("Número de semanas ínvalido");
            }

            System.out.print("Segunda[1]\nTerça[2]\nQuarta[3]\nQuinta[4]\nSexta[5]\n");//escolhe o dia da semana
            System.out.print("Indique o dia da semana do pagamento: ");
            
            while(true)
            {
                aux = Validar.int_valido();

                if(aux >= 1 && aux <= 5)
                {   
                    if(aux == 1)
                    {
                        nova += "MONDAY";
                    }

                    else if(aux == 2)
                    {
                        nova += "TUESDAY";
                    }
                    else if(aux == 3)
                    {
                        nova += "WEDNESDAY";
                    }
                    else if(aux == 4)
                    {
                        nova += "THURSDAY";
                    }
                    else if(aux == 5)
                    {
                        nova += "FRIDAY";
                    }
                    System.out.println(nova);    
                    break;
                }

                System.out.println("Opção ínvalida\n");
            }

            methods.add(nova);
            Main.clear();
        }

        else//opcao mensal
        {
            Main.clear();
            nova = "Mensal";

            int aux;
            System.out.print("Digite [1] se deseja que o pagamento ocorra no ultimo dia útil\n");
            System.out.print("Digite [0] para escolher outro dia\n");
            System.out.print("DIGITE: ");
            aux = Validar.int_valido();

            Main.clear();
            if(aux == 1)
            {
                nova += " $";
                System.out.println(nova);    
                methods.add(nova);
            }

            else
            {
                System.out.print("Indique o dia do pagamento: ");
                aux = Validar.dia_valido();
                String str = Integer.toString(aux);
                nova += " ";
                nova += str;
                nova += " ";
                
                System.out.println(nova);    
                methods.add(nova);
            }

           Main.clear();
        }    
        System.out.println(nova);    
    }
}

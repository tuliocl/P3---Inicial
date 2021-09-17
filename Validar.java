import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Validar 
{
    public static Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);

    public static int tipo_funcionario()//Só deixa entrar Horista ou assalariado
    {
        while(true)
        {
            System.out.print("Opções de funcionario: Horista [1], Assalariado[2]\n");
            System.out.print("Digite o tipo de funcionario (CODIGO): ");
            String tipo = input.nextLine();

            if(tipo.equals("1") || tipo.equals("2"))
            {
                int tipo_real = Integer.parseInt(tipo);
                return tipo_real;
            }
    
            else
            {
                Main.clear();   
            }
        }
    }

    public static String string_valida()
    {
        String valida;
        while(true)
        {
            valida = input.nextLine();

            if(!valida.isBlank())
            {
                break;
            }

            else
            {
                System.out.print("Informação invalida!\nDigite novamente: ");
            }
        }  

        return valida;
    }

    public static double verifica_dinheiro()
    {
        String valor;

        while(true)
        {
            valor = input.nextLine();

            boolean flag = true;

            for(int i = 0;i < valor.length();i++)
            {
                if(!((valor.charAt(i) >= '0' && valor.charAt(i) <= '9') || valor.charAt(i) == '.'))
                {
                    flag = false;
                    break;
                }
            }

            if(!valor.isBlank() && flag == true)//pega string vazia e strings nao validas
            {
                break;
            }
            
            else
            {
                System.out.print("Informação invalida!\nDigite novamente: ");
            }
            
        }

        double valor_real = Double.parseDouble(valor);
        return valor_real;
    }

    public static boolean verifica_boolean()
    {
        while(true)
        {
            String condicao = input.nextLine();

            if(condicao.equals("1") || condicao.equals("2"))
            {
                if(condicao.equals("1"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            
            else
            {
                System.out.print("Informação invalida!\nDigite novamente: ");
            }   
        }
    }
    
    public static int int_valido()
    {
        String inteiro_converter;

        while(true)
        {
            inteiro_converter = input.nextLine();

            boolean check = true;
            
            for(int i = 0;i < inteiro_converter.length();i++)
            {
                if(!(inteiro_converter.charAt(i) >= '0' && inteiro_converter.charAt(i) <= '9'))
                {
                    check = false;
                    break;
                }
            }

            if(!inteiro_converter.isBlank() && check == true)
            {
                break;
            }

            else
            {
                System.out.print("Informação invalida!\nDigite novamente: ");
            }  
        }

        int inteiro_valido = Integer.parseInt(inteiro_converter);
        return inteiro_valido;
    }

    public static int dia_valido()
    {
        String dia_converter;

        while(true)
        {
            dia_converter = input.nextLine();

            boolean check = true;
            
            for(int i = 0;i < dia_converter.length();i++)
            {
                if(!(dia_converter.charAt(i) >= '0' && dia_converter.charAt(i) <= '9'))
                {
                    check = false;
                    break;
                }
            }

            if(!dia_converter.isBlank() && check == true)
            {
                int aux = Integer.parseInt(dia_converter);
                if(aux >= 1 && aux <= 31)
                {
                    return aux;
                }

                else
                {
                    System.out.print("Informação invalida!\nDigite novamente: ");
                } 
            }

            else
            {
                System.out.print("Informação invalida!\nDigite novamente: ");
            }  
        }
    }

    public static int hora_valida()
    {
        String hora_converter;

        while(true)
        {
            hora_converter = input.nextLine();

            boolean check = true;
            
            for(int i = 0;i < hora_converter.length();i++)
            {
                if(!(hora_converter.charAt(i) >= '0' && hora_converter.charAt(i) <= '9'))
                {
                    check = false;
                    break;
                }
            }

            if(!hora_converter.isBlank() && check == true)
            {
                int aux = Integer.parseInt(hora_converter);
                if(aux >= 0 && aux <= 23)
                {
                    return aux;
                }
                else
                {
                    System.out.print("Informação invalida!\nDigite novamente: ");
                } 
            }

            else
            {
                System.out.print("Informação invalida!\nDigite novamente: ");
            }  
        }
    }

    public static void listhourly(ArrayList<Hourly> listofhourly)
    {
        for(int i = 0;i < listofhourly.size();i++)
        {
            Hourly show = listofhourly.get(i);
            System.out.println(show.getNome() + "ID: " + show.getId());
        }
    }

    public static void listsalaried(ArrayList<Salaried> listofsalaried)
    {
        for(int i = 0;i < listofsalaried.size();i++)
        {
            Salaried show = listofsalaried.get(i);
            System.out.println(show.getNome() + "ID: " + show.getId());
        }
    }
}
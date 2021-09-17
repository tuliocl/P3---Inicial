import java.time.LocalDate;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;

public class PayRoll 
{
    static Scanner input = new Scanner(System.in);
    static boolean flag = true;

    public static void Pagamento(PaymentSchedule agenda,ArrayList<Hourly> horistas,ArrayList<Salaried> assalariados)
    {
        Main.clear();

        LocalDate date = LocalDate.now();
        Calendar calendar = Calendar.getInstance();

        for(int i = 0;i < agenda.methods.size();i++)//i indica o codigo da agenda
        {
            String atual = agenda.methods.get(i);
            boolean search = false;

            if(atual.contains("Semanal"))
            {
                search = verify_weekly(atual,date,calendar);
            }

            else if(atual.contains("Mensal"))
            {
                search = verify_monthly(atual,date,calendar);
            }

            if(search)
            {
                roll(horistas,assalariados,i,atual);
            }
        }

        if(flag)
        {
            System.out.println("Nenhum empregado é pago hoje");
        }
        System.out.println("aperte ENTER para voltar");
        input.nextLine();
        flag = true;
        Main.clear();
    }

    private static boolean verify_weekly(String atual,LocalDate date,Calendar calendar)
    {
        String dayname = date.getDayOfWeek().name();
        int week_number = calendar.get(Calendar.WEEK_OF_MONTH);

        String[] parts = atual.split(" ");
        int week_of_payment = Integer.parseInt(parts[1]);//pega a quantidade de semanas

        if(atual.contains(dayname))
        {
            if(week_number % week_of_payment == 0)
            {
                return true;
            }
        }

        return false;
    }

    private static boolean verify_monthly(String atual,LocalDate date,Calendar calendar)
    {
        int day = date.getDayOfMonth();

        String[] parts = atual.split(" ");
        int day_of_payment = -1;
        
        if(parts[1].equals("$"))
        {
            day_of_payment = 1;
        }

        else
        {
            day_of_payment = Integer.parseInt(parts[1]);//pega o dia do pagamento
        }

        if(day == day_of_payment)
        {
            return true;
        }
        
        return false;
    }

    private static void roll(ArrayList<Hourly> horistas,ArrayList<Salaried> assalariados,int i,String atual)
    {
        for(int j = 0;j < assalariados.size();j++)
        {
            Salaried salaried = assalariados.get(j);
            if(salaried.schedule == i)
            {
                calculate_assalariado(salaried,atual);
                flag = false;
            }
        } 
        
        for(int j = 0;j < horistas.size();j++)
        {
            Hourly hourly = horistas.get(j);
            if(hourly.schedule == i)
            {
                calculate_hourly(hourly,atual);
                flag = false;
            }
        }
    }

    private static void calculate_assalariado(Salaried aux,String atual)
    {
        double salary = 0.0;

        if(atual.contains("Mensal"))
        {
            salary = aux.salary;

            if(aux.iscommissioned)
            {
                salary += Salaried.Total_sales(aux);
            }

            if(aux.sindicate)
            {
                salary -= Sindicate.Calculate_taxes(aux);
            }
        }

        else if(atual.contains("Semanal"))
        {
            salary = aux.salary;

            if(aux.iscommissioned)
            {
                salary += Salaried.Total_sales(aux);
            }

            String[] parts = atual.split(" ");
            int week_of_payment = Integer.parseInt(parts[1]);//pega a quantidade de semanas
            salary = salary * (0.25 * week_of_payment);

            if(aux.sindicate)
            {
                salary -= Sindicate.Calculate_taxes(aux);
            }
        }
       
        print(aux.name,salary,aux.payment_method);
    }

    private static void calculate_hourly(Hourly aux,String atual)
    {
        double salary = 0.0;

        salary += Hourly.calculate_payment(aux);
        salary -= Sindicate.Calculate_taxes(aux);

        print(aux.name,salary,aux.payment_method);
        
    }

    private static void print(String name,double salary,int option)
    {
        String metodo = "NULL";
        if(option == 1)
        {
            metodo = "Cheque correio";
        }

        else if(option == 2)
        {
            metodo = "Cheque-Mãos";
        }
        else if(option == 3)
        {
            metodo = "Deposito";
        }

        System.out.printf("O empregado %s, recebeu %.2f atraves do pagamento %s\n",name,salary,metodo);
    }
}
import java.util.ArrayList;

public class Hourly extends Employee
{
    ArrayList<TimeCard> days = new ArrayList<TimeCard>();
    
    public static Hourly add_hourly(Hourly novo,int id,int id_sindicato)
    {    
        novo.type = 1;
        novo.id = id;

        System.out.print("Digite o nome: ");
        novo.name = Validar.string_valida();
        Main.clear();

        System.out.print("Digite a cidade: ");
        novo.city = Validar.string_valida();
        Main.clear();

        while(true)
        {
            System.out.print("Digite a agenda de pagamento\n");
            Functions.listofschedule.print();
            System.out.print("Código da escolha: ");
            novo.schedule = Validar.int_valido();

            if(novo.schedule >= 0 && novo.schedule < Functions.listofschedule.methods.size())
            {
                Main.clear();
                break;
            }

            Main.clear();
        }

        while(true)
        {
            System.out.print("Digite o Metodo de pagamento\n");
            System.out.print("[1] - Cheque correio\n[2] - Cheque-Mãos\n[3] - Deposito\n");
            System.out.print("Código da escolha: ");
            novo.payment_method = Validar.int_valido();
            Main.clear();

            if(novo.payment_method >= 1 && novo.payment_method <= 3)
            {
                Main.clear();
                break;
            }
            
            Main.clear();
        }

        System.out.print("Digite o salario: R$ ");
        novo.salary = Validar.verifica_dinheiro();
        Main.clear();

        System.out.print("Pertence ao sindicato? Sim[1] Não[2]: ");
        novo.sindicate = Validar.verifica_boolean();
        Main.clear();

        if(novo.sindicate)
        {
            novo.sindicate_data.sindicate_id = id_sindicato;

            System.out.print("Digite a taxa sindical: R$ ");
            novo.sindicate_data.mensal_taxe = Validar.verifica_dinheiro();
            Main.clear();
        }

        return novo;
    }
    
    public static void add_Timecard(Hourly aux)
    {
        System.out.print("Indique o dia [dd]: ");
        int dia = Validar.dia_valido();

        System.out.print("Indique a hora de entrada: ");
        int in = Validar.hora_valida();

        System.out.print("Indique a hora de saida: ");
        int out = Validar.hora_valida();

        TimeCard novo = new TimeCard(dia,in,out);
        aux.days.add(novo);

        Main.clear();
    }

    public static double calculate_payment(Hourly aux)
    {
        double total = 0.0;
        int hours;
        for(int i = 0;i < aux.days.size();i++)
        {
            TimeCard current_day = aux.days.get(i);
            hours = current_day.hours_day;
            
            if(hours <= 8)
            {
                total += hours * aux.salary;
            }

            else
            {
                int extra = hours - 8;

                total += 8 * aux.salary;
                total += extra * (aux.salary * 1.5);
            }
        } 

        return total;
    }

    public static void print(Hourly aux)
    {
        System.out.printf("NOME              : %s\n",aux.name);
        System.out.printf("CIDADE            : %s\n",aux.city);

        System.out.printf("PAGAMENTO POR HORA: R$ %.2f\n",aux.salary);
       // System.out.printf("PAGAMENTO         : %s\n",Functions.listofschedule.methods.get(aux.schedule));
        if(aux.sindicate)
        {
            System.out.printf("TAXA SINDICAL     : R$ %.2f\n",aux.sindicate_data.mensal_taxe);
            System.out.printf("ID SINDICATO      : %d\n",aux.sindicate_data.sindicate_id);
            System.out.printf("TAXAS\n");

            for(int i = 0;i < aux.sindicate_data.taxes.size();i++)
            {
                Sale nova = aux.sindicate_data.taxes.get(i);
                System.out.printf("DIA %d: %.1f\n",nova.day,nova.value);
            }
        }

        System.out.printf("HORAS POR DIA\n");

        for (int i = 0;i < aux.days.size(); i++) 
        {
            TimeCard current_day = aux.days.get(i);

            if(current_day.hours_day != 0)
            {
                System.out.printf("DIA %d: %d\n",current_day.day,current_day.hours_day);
            }    
        }

        System.out.printf("ID                : %d\n",aux.id);
    }

    public String getNome() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Hourly clone(Hourly aux)
    {
        Hourly clone = new Hourly();
        clone.type = 2;
        clone.id = aux.id;
        clone.name = aux.name;
        clone.city = aux.city;
        clone.payment_method = aux.payment_method;
        clone.schedule = aux.schedule;
        clone.salary = aux.salary;
        clone.sindicate = aux.sindicate;
            
        if(clone.sindicate)
        {
            clone.sindicate_data.sindicate_id = aux.sindicate_data.sindicate_id;
            clone.sindicate_data.mensal_taxe = aux.sindicate_data.mensal_taxe;

            for(int i = 0;i < aux.sindicate_data.taxes.size(); i++) // passa as taxas
            {
                Sale seila = aux.sindicate_data.taxes.get(i);
                Sale nova = new Sale();

                nova.day = seila.day;
                nova.value = seila.value;

                clone.sindicate_data.taxes.add(nova);
            }
        }

        for(int i = 0;i < aux.days.size();i++)
        {   
            TimeCard seila2 = aux.days.get(i);
            TimeCard novo = new TimeCard();

            novo.day = seila2.day;
            novo.in = seila2.in;
            novo.out = seila2.out;
            novo.hours_day = seila2.hours_day;

            clone.days.add(novo);
        }
        
        return clone;
    }
}
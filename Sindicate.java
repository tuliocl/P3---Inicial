import java.util.ArrayList;

public class Sindicate 
{  
    int sindicate_id;
    double mensal_taxe;
    ArrayList<Sale> taxes = new ArrayList<Sale>();

    public static void adiciona_taxa(Employee aux, int dia, double valor)
    {
        Sale nova = new Sale(dia,valor);
        aux.sindicate_data.taxes.add(nova);
    }

    public static double Calculate_taxes(Employee aux)
    {
        double total = 0.0;

        for(int i = 0;i < aux.sindicate_data.taxes.size();i++)
        {
            Sale taxa = aux.sindicate_data.taxes.get(i);
            total += taxa.value;
        }

        return total + aux.sindicate_data.mensal_taxe;
    }
}

import StackQueueTree.*;
import java.util.Iterator;
public class Warehouse extends BinarySearchTree<Product>
{
    // search feature has been updated
    private Product searchForProduct(String name)
    {
        if (isEmpty())
        {
            return null;
        }
        Product p = new Product(name, 0);
        Iterator<Product> iter = getPostorderIterator();
        do
        {
            Product here = iter.next();
            if (p.getName().toLowerCase().equals(here.getName().toLowerCase()))
            {
                return here;
            }
        } while (iter.hasNext());
        return null;
    }
    // remove feature has been updated
    public Product remove(String name)
    {
        Product toBeRemoved = searchForProduct(name);
        if (toBeRemoved != null) {remove(toBeRemoved);}
        return toBeRemoved;
    }
    // Initialize the inventory of the warehouse with several items
    public void initialize()
    {
        clear();
        add(new Product("Apple", 0.5));
        add(new Product("Banana", 0.45));
        add(new Product("Orange", 0.6));
        add(new Product("Grapes", 0.8));
        add(new Product("Mango", 0.75));
    }
    // test the warehouse feature before integrating it into the supermarket
    public static void main(String[] args)
    {
        Warehouse sample = new Warehouse();
        Product g = sample.add(new Product("Apple",0.5));
        // if (g.equals(null)) {System.out.println("Product added: " + g.toString());}
        Product f = sample.remove(new Product("Apple",0.5));
        if (f != null) {System.out.println("Product removed: " + f.toString());}
        Product c = sample.add(new Product("Banana",0.45));
        System.out.println("Result of searchForProduct(Banana): " +sample.searchForProduct("Banana"));
        System.out.println("Removed product " + sample.remove("Banana"));
        sample.initialize();
    }
}

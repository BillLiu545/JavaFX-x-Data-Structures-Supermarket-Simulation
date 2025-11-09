
public class SupermarketCart extends LinkedBag<Product>
{
    // search method to find a product given its name
    private Product searchForProduct(String name)
    {
        Object[] arr = toArray();
        for (Integer i = 0; i < arr.length; i++)
        {
            if (name.toLowerCase().equals(((Product)arr[i]).getName().toLowerCase()))
            {
                return (Product)arr[i];
            }
        }
        return null;
    }
    // updated remove method
    public Product remove(String name)
    {
        Product toBeRemoved = searchForProduct(name);
        if (remove(toBeRemoved))
        {
            return toBeRemoved;
        }
        else
        {
            return null;
        }
    }
    // Get total cost of the cart
    public double getTotalCost()
    {
        if (getCurrentSize()==0)
        {
            return 0;
        }
        double total = 0;
        Object[] arr = toArray();
        for (Integer i = 0; i < arr.length; i++)
        {
            total += ((Product)arr[i]).getCost();
        }
        return total;
    }
    // Get the full list of items in cart
    public String listAllItems()
    {
        if (getCurrentSize()==0)
        {
            return "No items currently in cart";
        }
        StringBuilder sb = new StringBuilder("All products in cart: ");
        Object[] arr = toArray();
        for (Integer i = 0; i < arr.length; i++)
        {
            sb.append("\n"+((Product)arr[i]).toString()+" ");
        }
        return sb.toString();
    }
    // Test methods before integration
    public static void main(String[] args)
    {
        SupermarketCart cart = new SupermarketCart();
        Product example = new Product("Apple",0.5);
        if (cart.add(example)){
            System.out.println("Product added: " + example.toString());
        }
        Product toBeRemoved = cart.remove("Apple");
        if (toBeRemoved!=null)
        {
            System.out.println("Product removed: " + toBeRemoved.toString());
        }
    }
}

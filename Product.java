public class Product implements Comparable<Product>
{
    private String name; private double cost;
    public Product(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Double getCost()
    {
        return this.cost;
    }
    
    public int compareTo(Product other)
    {
        if (other.name.equals(this.name))
        {
            return 0;
        }
        return 1;
    }
    
    public String toString()
    {
        return "Name: " + this.name + " - Cost: " + this.cost;
    }
}

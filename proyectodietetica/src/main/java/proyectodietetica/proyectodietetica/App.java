package proyectodietetica.proyectodietetica;
import java.util.Date;
import java.util.Scanner;
import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import model.Cliente;
import model.Factura;
import model.Producto;

public class App 
{
    private static ProductoDAO productoDAO = new ProductoDAO();
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static FacturaDAO facturaDAO = new FacturaDAO();
    
    public static void main( String[] args )
    {
    	 Scanner scanner = new Scanner(System.in);
         int option;

         do {
         
             System.out.println("\n1. Agregar producto");
             System.out.println("2. Agregar cliente");
             System.out.println("3. Agregar factura");
             System.out.println("4. Listar productos");
             System.out.println("5. Listar clientes");
             System.out.println("6. Listar facturas");
             System.out.println("7. Salir");
             
             System.out.print("\nSeleccione una opción: ");
             option = scanner.nextInt();

             switch (option) {
                 case 1:
                     agregarProducto(scanner);
                     break;
                 case 2:
                     agregarCliente(scanner);
                     break;
                 case 3:
                     agregarFactura(scanner);
                     break;
                 case 4:
                     listarProductos();
                     break;
                 case 5:
                     listarClientes();
                     break;
                 case 6:
                     listarFacturas();
                     break;
                 case 7:
                     System.out.println("Saliendo...");
                     break;
                 default:
                     System.out.println("Opción inválida.");
             }
         } while (option != 7);

         scanner.close();
     }

     private static void agregarProducto(Scanner scanner) {
         System.out.print("ID del producto: ");
         int id = scanner.nextInt();
         scanner.nextLine(); 
         System.out.print("Nombre del producto: ");
         String nombre = scanner.nextLine();
         System.out.print("Stock: ");
         int stock = scanner.nextInt();
         System.out.print("Precio: ");
         double precio = scanner.nextDouble();

         Producto producto = new Producto(id, nombre, stock, precio);
         productoDAO.crear(producto);
        
     }

     private static void agregarCliente(Scanner scanner) {
         System.out.print("ID del cliente: ");
         int id = scanner.nextInt();
         System.out.print("DNI: ");
         int dni = scanner.nextInt();
         scanner.nextLine();
         System.out.print("Nombre del cliente: ");
         String nombre = scanner.nextLine();
         System.out.print("Email: ");
         String email = scanner.nextLine();
         System.out.print("Teléfono: ");
         int telefono = scanner.nextInt();

         Cliente cliente = new Cliente(id, dni, nombre, email, telefono);
         clienteDAO.crear(cliente);
 
     }

     private static void agregarFactura(Scanner scanner) {
         System.out.print("Número de factura: ");
         int numero = scanner.nextInt();
         System.out.print("ID del cliente: ");
         int idCliente = scanner.nextInt();
         System.out.print("ID del producto: ");
         int idProducto = scanner.nextInt();
         System.out.print("Cantidad: ");
         int cantidad = scanner.nextInt();
         System.out.print("Total: ");
         double total = scanner.nextDouble();

         Factura factura = new Factura(numero, idCliente, idProducto, new Date(), cantidad, total);
         facturaDAO.crear(factura);
        
     }

     private static void listarProductos() {
         System.out.println("Productos:\n");
         for (Producto producto : productoDAO.listar()) {
             System.out.println(producto);
         }
     }

     private static void listarClientes() {
         System.out.println("Clientes:\n");
         for (Cliente cliente : clienteDAO.listar()) {
             System.out.println(cliente);
         }
     }

     private static void listarFacturas() {
         System.out.println("Facturas:\n");
         for (Factura factura : facturaDAO.listar()) {
             System.out.println(factura);
         }
     }
     
    }


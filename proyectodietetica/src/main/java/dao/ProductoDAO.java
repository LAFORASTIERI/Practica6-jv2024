package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import proyectodietetica.proyectodietetica.Conexion;

public class ProductoDAO implements DAO <Producto>  {

    @Override
    public void crear(Producto producto) {
        Connection con = Conexion.conectar();
        try {
            // Iniciar transacción 
            con.setAutoCommit(false);
            
            // Crear query
            String query = "INSERT INTO Producto (idProducto, nombreProducto, stock, precio) VALUES (?, ?, ?, ?)";

            // Preparar el estado
            PreparedStatement stmt = con.prepareStatement(query);
            
            // Settear las variables incognitas
            stmt.setInt(1, producto.getIdProducto());
            stmt.setString(2, producto.getNombreProducto());
            stmt.setInt(3, producto.getStock());
            stmt.setDouble(4, producto.getPrecio());

            // Ejecutar el query
            stmt.execute();
            System.out.println("Producto agregado correctamente.");
            con.commit();

            // Cerrar conexiones
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR PRODUCTO");
            e.printStackTrace();
            try {
                System.out.println("REVIERTO TRANSACCION");
                con.rollback();
            } catch (Exception e2) {
                System.out.println("ERROR AL REVERTIR: " + e2.getMessage());
            }
        }
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        Connection con = Conexion.conectar();
        try {
            // Crear query
            String query = "SELECT * FROM Producto";

            // Preparar el estado
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Producto producto = new Producto(
                    resultado.getInt("idProducto"),
                    resultado.getString("nombreProducto"),
                    resultado.getInt("stock"),
                    resultado.getDouble("precio")
                );
                productos.add(producto);
            }
            
            // Cerrar conexiones
            resultado.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR PRODUCTOS");
            e.printStackTrace();
        }
        return productos;
    }
}

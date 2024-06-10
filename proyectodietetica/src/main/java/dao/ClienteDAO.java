package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import proyectodietetica.proyectodietetica.Conexion;

public class ClienteDAO implements DAO<Cliente> {
	
	@Override
    public void crear(Cliente cliente) {

	        Connection con = Conexion.conectar();
	        try {
	            // Iniciar transacción 
	            con.setAutoCommit(false);
	            
	            // Crear query
	            String query = "INSERT INTO Cliente (idCliente, dni, nombreCliente, email, telefono) VALUES (?, ?, ?, ?, ?)";

	            // Preparar el estado
	            PreparedStatement stmt = con.prepareStatement(query);
	            
	            // Settear las variables incognitas
	            stmt.setInt(1, cliente.getIdCliente());
	            stmt.setInt(2, cliente.getDni());
	            stmt.setString(3, cliente.getNombreCliente());
	            stmt.setString(4, cliente.getEmail());
	            stmt.setInt(5, cliente.getTelefono());

	            // Ejecutar el query
	            stmt.execute();
	            System.out.println("Cliente agregado correctamente.");
	            con.commit();

	            // Cerrar conexiones
	            stmt.close();
	            con.close();
	        } catch (Exception e) {
	            System.out.println("ERROR AL INSERTAR CLIENTE");
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
	    public List<Cliente> listar() {
	        List<Cliente> clientes = new ArrayList<>();
	        Connection con = Conexion.conectar();
	        try {
	            // Crear query
	            String query = "SELECT * FROM Cliente";

	            // Preparar el estado
	            PreparedStatement stmt = con.prepareStatement(query);
	            ResultSet resultado = stmt.executeQuery();
	            while (resultado.next()) {
	                Cliente cliente = new Cliente(
	                    resultado.getInt("idCliente"),
	                    resultado.getInt("dni"),
	                    resultado.getString("nombreCliente"),
	                    resultado.getString("email"),
	                    resultado.getInt("telefono")
	                );
	                clientes.add(cliente);
	            }
	            
	            // Cerrar conexiones
	            resultado.close();
	            stmt.close();
	            con.close();
	        } catch (Exception e) {
	            System.out.println("ERROR AL LISTAR CLIENTES");
	            e.printStackTrace();
	        }
	        return clientes;
	    }
	}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Factura;
import proyectodietetica.proyectodietetica.Conexion;

public class FacturaDAO implements DAO<Factura> {

	@Override
    public void crear(Factura factura) {
        Connection con = Conexion.conectar();
        try {
            // Iniciar transacción 
            con.setAutoCommit(false);
            
            // Crear query
            String query = "INSERT INTO Factura (numeroFactura, idCliente, idProducto, fecha, cantidad, total) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparar el estado
            PreparedStatement stmt = con.prepareStatement(query);
            
            // Settear las variables incognitas
            stmt.setInt(1, factura.getNumeroFactura());
            stmt.setInt(2, factura.getIdCliente());
            stmt.setInt(3, factura.getIdProducto());
            stmt.setDate(4, new java.sql.Date(factura.getFecha().getTime()));
            stmt.setInt(5, factura.getCantidad());
            stmt.setDouble(6, factura.getTotal());

            // Ejecutar el query
            stmt.execute();
            System.out.println("Factura agregada correctamente.");
            con.commit();

            // Cerrar conexiones
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR FACTURA");
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
    public List<Factura> listar() {
        List<Factura> facturas = new ArrayList<>();
        Connection con = Conexion.conectar();
        try {
            // Crear query
            String query = "SELECT * FROM Factura";

            // Preparar el estado
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Factura factura = new Factura(
                    resultado.getInt("numeroFactura"),
                    resultado.getInt("idCliente"),
                    resultado.getInt("idProducto"),
                    resultado.getDate("fecha"),
                    resultado.getInt("cantidad"),
                    resultado.getDouble("total")
                );
                facturas.add(factura);
            }
            
            // Cerrar conexiones
            resultado.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR FACTURAS");
            e.printStackTrace();
        }
        return facturas;
    }
}
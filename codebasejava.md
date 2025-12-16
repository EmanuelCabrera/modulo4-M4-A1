import java.util.Objects;

// --- Interfaces ---
interface ISuperOrderProcessor {
    void processOrder(String type);
    void calculateTax();
    void saveToMySQL();
    void sendEmailConfirmation();
    void generatePDFReport();
}

// --- Clases Concretas (Simulando persistencia) ---
class MySQLDatabase {
    public void insert() {
        System.out.println("Insertando en base de datos MySQL...");
    }
}

// --- Clase Principal con violaciones SRP, OCP, DIP ---
class OrderManager implements ISuperOrderProcessor {

    // Violación DIP: Dependencia concreta fuerte
    private MySQLDatabase database = new MySQLDatabase();

    @Override
    public void processOrder(String type) {
        // Violación OCP: Modificar esto para cada nuevo tipo
        if (type.equals("Standard")) {
            System.out.println("Procesando orden estándar...");
        } else if (type.equals("Express")) {
            System.out.println("Procesando orden express...");
        } else {
            System.out.println("Tipo desconocido");
        }
        
        // Violación SRP: El gestor orquesta demasiadas cosas distintas
        calculateTax();
        saveToMySQL();
        sendEmailConfirmation();
        generatePDFReport();
    }

    @Override
    public void calculateTax() {
        System.out.println("Calculando impuestos complejos...");
    }

    @Override
    public void saveToMySQL() {
        database.insert();
    }

    @Override
    public void sendEmailConfirmation() {
        System.out.println("Enviando correo...");
    }

    @Override
    public void generatePDFReport() {
        System.out.println("Generando PDF...");
    }
}

// --- Subclase que rompe el comportamiento ---
class ReadOnlyOrderManager extends OrderManager {

    // Violación LSP: Cambia el comportamiento base lanzando una excepción
    @Override
    public void saveToMySQL() {
        throw new UnsupportedOperationException("No puedo guardar, soy de solo lectura.");
    }

    // Violación ISP: Obligado a implementar métodos que no usa o deja vacíos
    @Override
    public void sendEmailConfirmation() {
        // No hace nada
    }
}

public class OrderSystem {
    public static void main(String[] args) {
        System.out.println("--- Orden Normal ---");
        OrderManager order = new OrderManager();
        order.processOrder("Standard");

        System.out.println("\n--- Intentando usar la subclase (Violación LSP) ---");
        OrderManager badOrder = new ReadOnlyOrderManager();
        try {
            // El polimorfismo debería permitirnos tratar a badOrder igual que a order, pero falla.
            badOrder.saveToMySQL();
        } catch (Exception e) {
            System.out.println("Error crítico: " + e.getMessage());
        }
    }
}
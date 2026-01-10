package com.example.controlador;

import com.example.vista.VistaCombate;
import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.Dragon;
import com.example.modelo.Hechizo;
import com.example.modelo.Bosque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Clase ControladorCombate - Gestiona la lógica del combate automático
 */
public class ControladorCombate {

    private VistaCombate vista;
    private Random random;
    private List<Mago> magosMuertos;
    private List<Monstruo> monstruosMuertos;

    public ControladorCombate() {
        this.vista = new VistaCombate();
        this.random = new Random();
        this.magosMuertos = new ArrayList<>();
        this.monstruosMuertos = new ArrayList<>();
    }

    /**
     * Inicia el combate automático según las reglas establecidas
     */
    public void iniciarCombate(List<Mago> magos, List<Monstruo> monstruos, Dragon dragon, 
                               List<Hechizo> hechizosDisponibles, Bosque bosque) {
        
        // Validaciones iniciales
        if (monstruos.isEmpty() || magos.isEmpty()) {
            if (monstruos.isEmpty()) {
                vista.mostrarNoHayMonstruos();
            }
            if (magos.isEmpty()) {
                vista.mostrarNoHayMagos();
            }
            return;
        }

        // Inicializar listas de combate
        List<Mago> magosVivos = new ArrayList<>(magos);
        List<Monstruo> monstruosVivos = new ArrayList<>(monstruos);
        magosMuertos.clear();
        monstruosMuertos.clear();
        int numeroRonda = 1;

        vista.mostrarInicioCombate();

        // Bucle principal del combate
        while (!magosVivos.isEmpty() && !monstruosVivos.isEmpty()) {
            vista.mostrarInicioRonda(numeroRonda);

            // a) Fase de Magos
            faseMagos(magosVivos, monstruosVivos, hechizosDisponibles);

            // b) Fase de Monstruos (solo si quedan magos y monstruos)
            if (!magosVivos.isEmpty() && !monstruosVivos.isEmpty()) {
                faseMonstruos(magosVivos, monstruosVivos);
            }

            // c) Fase del Dragón (solo si hay dragón y monstruos)
            if (dragon != null && dragon.getResistencia() > 0 && !monstruosVivos.isEmpty()) {
                faseDragon(dragon, bosque, monstruosVivos);
            }

            // d) Limpieza - Eliminar muertos
            limpiarMuertos(magosVivos, monstruosVivos, bosque);

            // e) Reporte de estado
            vista.mostrarReporte(magosVivos, magosMuertos, monstruosVivos, monstruosMuertos, bosque);

            numeroRonda++;
        }

        // Determinar ganador
        comprobarGanador(magosVivos, monstruosVivos);
    }

    /**
     * Fase de Magos: Cada mago intenta lanzar UN hechizo de los disponibles
     * Si lo conoce, lo lanza. Si no lo conoce, pierde 1 punto de vida.
     */
    private void faseMagos(List<Mago> magosVivos, List<Monstruo> monstruosVivos, 
                           List<Hechizo> hechizosDisponibles) {
        vista.mostrarFaseMagos();
        
        for (Mago mago : magosVivos) {
            vista.mostrarTurnoMago(mago.getNombre());
            
            // Cada mago intenta lanzar UN hechizo aleatorio de los disponibles
            if (!hechizosDisponibles.isEmpty() && !monstruosVivos.isEmpty()) {
                Hechizo hechizoSeleccionado = hechizosDisponibles.get(random.nextInt(hechizosDisponibles.size()));
                
                if (mago.conoceHechizo(hechizoSeleccionado)) {
                    // El mago conoce el hechizo, lo lanza
                    vista.mostrarLanzamientoHechizo(mago.getNombre(), hechizoSeleccionado.getNombre());
                    
                    // Aplicar hechizo según su tipo
                    if (hechizoSeleccionado.getNombre().equals("Bola de fuego")) {
                        // Bola de fuego ataca a TODOS los monstruos
                        hechizoSeleccionado.aplicar(monstruosVivos);
                    } else {
                        // Rayo y Bola de nieve atacan a UN monstruo aleatorio
                        Monstruo monstruoObjetivo = monstruosVivos.get(random.nextInt(monstruosVivos.size()));
                        hechizoSeleccionado.aplicar(monstruoObjetivo);
                    }
                } else {
                    // El mago NO conoce el hechizo, pierde 1 punto de vida
                    mago.setVida(mago.getVida() - 1);
                    vista.mostrarPenalizacionHechizo(mago.getNombre(), hechizoSeleccionado.getNombre());
                }
            }
        }
    }

    /**
     * Fase de Monstruos: Cada monstruo ataca a un mago aleatorio
     */
    private void faseMonstruos(List<Mago> magosVivos, List<Monstruo> monstruosVivos) {
        vista.mostrarFaseMonstruos();
        
        for (Monstruo monstruo : monstruosVivos) {
            // Seleccionar mago aleatorio
            Mago magoObjetivo = magosVivos.get(random.nextInt(magosVivos.size()));
            
            // El monstruo ataca al mago
            int danio = monstruo.getFuerza();
            magoObjetivo.setVida(magoObjetivo.getVida() - danio);
            
            vista.monstruoAtaca(monstruo.getNombre(), magoObjetivo.getNombre(), danio);
        }
    }

    /**
     * Fase del Dragón: El dragón ataca al monstruo jefe
     */
    private void faseDragon(Dragon dragon, Bosque bosque, List<Monstruo> monstruosVivos) {
        vista.mostrarFaseDragon();
        
        Monstruo jefe = bosque.getMonstruoJefe();
        if (jefe != null && jefe.getVida() > 0) {
            dragon.exhalar(jefe);
            vista.mostrarAtaqueDragon(dragon.getNombre(), jefe.getNombre(), dragon.getIntensidadFuego());
        }
    }

    /**
     * Limpieza: Elimina magos y monstruos con vida <= 0
     */
    private void limpiarMuertos(List<Mago> magosVivos, List<Monstruo> monstruosVivos, Bosque bosque) {
        // Eliminar magos muertos
        Iterator<Mago> itMagos = magosVivos.iterator();
        while (itMagos.hasNext()) {
            Mago mago = itMagos.next();
            if (mago.getVida() <= 0) {
                magosMuertos.add(mago);
                itMagos.remove();
                vista.mostrarMagoMuerto(mago.getNombre());
            }
        }

        // Eliminar monstruos muertos
        Iterator<Monstruo> itMonstruos = monstruosVivos.iterator();
        while (itMonstruos.hasNext()) {
            Monstruo monstruo = itMonstruos.next();
            if (monstruo.getVida() <= 0) {
                monstruosMuertos.add(monstruo);
                itMonstruos.remove();
                vista.mostrarMonstruoMuerto(monstruo.getNombre());
                
                // Si el jefe murió, asignar nuevo jefe
                if (monstruo.equals(bosque.getMonstruoJefe())) {
                    asignarNuevoJefe(bosque, monstruosVivos);
                }
            }
        }
    }

    /**
     * Asigna un nuevo jefe de entre los monstruos vivos
     */
    private void asignarNuevoJefe(Bosque bosque, List<Monstruo> monstruosVivos) {
        if (!monstruosVivos.isEmpty()) {
            Monstruo nuevoJefe = monstruosVivos.get(0);
            bosque.setMonstruoJefe(nuevoJefe);
            vista.mostrarNuevoJefe(nuevoJefe.getNombre());
        } else {
            bosque.setMonstruoJefe(null);
        }
    }

    /**
     * Determina el ganador del combate
     */
    public void comprobarGanador(List<Mago> magosVivos, List<Monstruo> monstruosVivos) {
        vista.mostrarFinCombate();
        
        if (magosVivos.isEmpty() && monstruosVivos.isEmpty()) {
            vista.mostrarEmpate();
        } else if (monstruosVivos.isEmpty()) {
            vista.mostrarVictoriaMagos();
        } else {
            vista.mostrarGanadorMonstruos();
        }
    }
}
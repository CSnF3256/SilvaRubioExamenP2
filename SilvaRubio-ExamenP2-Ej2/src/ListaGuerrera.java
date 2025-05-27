import java.util.ArrayList;
import java.util.List;

public class ListaGuerrera {

        private NodoGuerrera inicio;
        private NodoGuerrera fin;
        private int tamano;

        public ListaGuerrera() {
            inicio = null;
            fin = null;
            tamano = 0;
        }
        public boolean insertarOrdenado(GuerreraBrightMoon v) {
            if (buscarPorID(v.getID()) != null) return false; // ya existe

            NodoGuerrera nuevo = new NodoGuerrera(v);
            if (inicio == null) {
                inicio = fin = nuevo;
            } else if (v.getID() < inicio.guerrera.getID()) {
                nuevo.siguiente = inicio;
                inicio.anterior = nuevo;
                inicio = nuevo;
            } else {
                NodoGuerrera actual = inicio;
                while (actual.siguiente != null && actual.siguiente.guerrera.getID() < v.getID()) {
                    actual = actual.siguiente;
                }
                nuevo.siguiente = actual.siguiente;
                nuevo.anterior = actual;
                if (actual.siguiente != null) actual.siguiente.anterior = nuevo;
                else fin = nuevo;
                actual.siguiente = nuevo;
            }
            tamano++;
            return true;
        }
//busqueda binaria
        public GuerreraBrightMoon buscarPorID(int id) {
            List<GuerreraBrightMoon> lista = toArray();
            int inicio = 0, fin = lista.size() - 1;
            while (inicio <= fin) {
                int medio = (inicio + fin) / 2;
                GuerreraBrightMoon v = lista.get(medio);
                if (v.getID() == id) return v;
                if (v.getID() < id) inicio = medio + 1;
                else fin = medio - 1;
            }
            return null;
        }

        public List<GuerreraBrightMoon> toArray() {
            List<GuerreraBrightMoon> lista = new ArrayList<>();
            NodoGuerrera actual = inicio;
            while (actual != null) {
                lista.add(actual.guerrera);
                actual = actual.siguiente;
            }
            return lista;
        }

        public List<GuerreraBrightMoon> filtrarPorPoderYOrdenar(String poder) {
            List<GuerreraBrightMoon> filtradas = new ArrayList<>();
            NodoGuerrera actual = inicio;
            while (actual != null) {
                if (actual.guerrera.getPoderBatalla().equals(poder)) {
                    filtradas.add(actual.guerrera);
                }
                actual = actual.siguiente;
            }
            // Ordenar por nivel de estrategia (mayor a menor) selección
            for (int i = 0; i < filtradas.size() - 1; i++) {
                int max = i;
                for (int j = i + 1; j < filtradas.size(); j++) {
                    if (filtradas.get(j).getNivelEstrategia() > filtradas.get(max).getNivelEstrategia()) {
                        max = j;
                    }
                }
                GuerreraBrightMoon temp = filtradas.get(i);
                filtradas.set(i, filtradas.get(max));
                filtradas.set(max, temp);
            }
            return filtradas;
        }

        public int contarPorUbicacion(String ubicacion) {
            return contarRecursivo(inicio, ubicacion);
        }

    private int contarRecursivo(NodoGuerrera nodo, String ubicacion) {
        if (nodo == null) {return 0;}
        int suma = 0;
        if (nodo.guerrera.getUbicacion().equals(ubicacion)) {
            suma = 1;
        }
        return suma + contarRecursivo(nodo.siguiente, ubicacion);
    }

}


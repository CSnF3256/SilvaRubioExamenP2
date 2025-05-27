public class NodoGuerrera {

        public GuerreraBrightMoon guerrera;
        public NodoGuerrera anterior;
        public NodoGuerrera siguiente;

        public NodoGuerrera(GuerreraBrightMoon guerrera) {
            this.guerrera = guerrera;
            this.anterior = null;
            this.siguiente = null;
        }

}

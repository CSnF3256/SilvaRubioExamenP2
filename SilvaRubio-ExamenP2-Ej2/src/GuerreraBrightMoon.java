public class GuerreraBrightMoon {
    int ID;
    String Alias;
    String PoderBatalla; //combobox
    int NivelEstrategia; //combobox
    String Ubicacion; //combobox

    public GuerreraBrightMoon(int ID, String alias, String poderBatalla, int nivelEstrategia, String ubicacion) {
        this.ID = ID;
        Alias = alias;
        PoderBatalla = poderBatalla;
        NivelEstrategia = nivelEstrategia;
        Ubicacion = ubicacion;
    }

    public int getID() {
        return ID;
    }

    public String getAlias() {
        return Alias;
    }

    public String getPoderBatalla() {
        return PoderBatalla;
    }

    public int getNivelEstrategia() {
        return NivelEstrategia;
    }

    public String getUbicacion() {
        return Ubicacion;
    }
}

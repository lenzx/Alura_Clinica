package med.voll.API.infra.errores;

public class ValidacionDeIntegracion extends RuntimeException {
    public ValidacionDeIntegracion(String s) {
        super(s);
    }
}

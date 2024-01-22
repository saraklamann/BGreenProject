
public class Principal{
    public static void main(String[] args ){ //finalizado (esqueleto)
        Aplicativo a = new Aplicativo();
        int opcao=0;
        boolean usuarioLogado=false;
        boolean empresaLogada=false;
        int n=0;
        String organizador = "";
        String cnpj="";

        do{
            usuarioLogado=false;
            n = 3;
            EntradaSaida.mostrarMenuGeral();
            opcao = EntradaSaida.pedirOpcao("");
            Validacao.validarOpcao(opcao, n);

            switch (opcao) {
                case 1:
                    Visitante.chamarMetodos(a);
                    break;
                case 2:
                    Voluntario v = Voluntario.logarVoluntario(a);
                    usuarioLogado=true;
                    Voluntario.chamarMetodos(a, organizador, usuarioLogado);
                    break;
                case 3:
                    empresaLogada=false;
                    Empresa e = Empresa.logarEmpresa(a);
                    empresaLogada=true;
                    Empresa.chamarMetodos(a, empresaLogada, e);
                    break;
            }
        } while (opcao!=0);
    }    
}
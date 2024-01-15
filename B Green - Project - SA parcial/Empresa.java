import java.util.ArrayList;

public class Empresa {
    private String nome;
    private String cnpj;
    private String usuarioResponsavel;
    private String senha;
    private String descricao;
    public Postos posto;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }
    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Postos getPosto() {
        return posto;
    }
    public void setPosto(Postos posto) {
        this.posto = posto;
    }

    public ArrayList<Postos> listaDePostosDeColeta = new ArrayList<Postos>();

    public static Postos adicionarPosto() {
        Postos p = new Postos();
                    p.cidade = EntradaSaida.pedirDados("a cidade em que se encontra o posto de coleta: ");
                    p.rua = EntradaSaida.pedirDados("a rua em que se encontra o posto de coleta: ");
                    p.numero = EntradaSaida.pedirDados("o numero em que se encontra o posto de coleta: ");
        return p;
    }

    public static void logarEmpresa(Aplicativo app) {
        int opcao = 0;
        String cnpj = EntradaSaida.pedirDados("O CNPJ da empresa para logar: ");
        while (app.procurarEmpresa(cnpj)==false) {
            System.out.println("Empresa não encontrada! Deseja cadastrar empresa ou tentar novamente? \n");
            opcao = EntradaSaida.pedirOpcao("1 - Cadastrar-se \n" +
            "2 - Tentar novamente\n");
            if (opcao==1) {
                Empresa empresa = new Empresa();
                empresa.setNome(EntradaSaida.pedirDados("o nome da empresa: "));
                empresa.setCnpj(EntradaSaida.pedirDados("o CNPJ da empresa: "));
                empresa.setSenha(EntradaSaida.pedirDados("a senha para a empresa: "));
                empresa.setUsuarioResponsavel(EntradaSaida.pedirDados("o usuário responsável pela empresa: "));
                empresa.setDescricao(EntradaSaida.pedirDados("para onde vai o material coletado: "));
                Postos p = new Postos();
                p.cidade = EntradaSaida.pedirDados("a cidade em que se localiza o posto de coleta: ");
                p.rua = EntradaSaida.pedirDados("a rua em que se localiza o posto de coleta: ");
                p.numero = EntradaSaida.pedirDados("o numero em que se localiza o posto de coleta: ");
                app.adicionarEmpresa(empresa);
            } else {
                cnpj = EntradaSaida.pedirDados("o CNPJ da empresa para logar: ");
                app.procurarEmpresa(cnpj);
            }
        }

        while(app.procurarEmpresa(cnpj)==false){
            System.out.println("Empresa não encontrada! ");
            cnpj = EntradaSaida.pedirDados("o CNPJ novamente: ");
            app.procurarEmpresa(cnpj);
        }

        boolean empresaLogada = app.verificarSeEmpresaLogada(cnpj);
        while(empresaLogada==false) {
            empresaLogada = app.logarEmpresa(cnpj);
        }
        System.out.println("Empresa logada com sucesso! ");
    }

    public static void chamarMetodos(Aplicativo app) {
        int opcao = 0;
        int n = 0;
        boolean empresaLogada = false;

        do{
            n = 7;
            opcao=0;
            if(empresaLogada==true){
                EntradaSaida.mostrarMenuEmpresa();
                opcao = EntradaSaida.pedirOpcao("");
                Validacao.validarOpcao(opcao, n);

                switch (opcao) {
                    case 1:   
                        EntradaSaida.mostrarEmpresasParceiras(app.listarEmpresas()); 
                        break;
                    case 2:
                        EntradaSaida.mostrarEventos(app.listarEventos());
                        break;
                    
                    case 3:
                        //adicionar/remover posto
                        break;
                    case 4:
                        //editar posto de coleta
                        break;
                    case 5:
                        //editar "para onde vai este material"
                    case 6:
                        empresaLogada = false;
                    case 0: 
                        System.exit(0);
                }

                if(opcao ==6){
                    empresaLogada = false;
                }
            }
        } while (opcao != 0);
    }

}

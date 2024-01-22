import java.util.ArrayList;

public class Voluntario {
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;

    public ArrayList <Evento> eventosMarcados = new ArrayList <Evento>();

    public void setEventosMarcados(ArrayList<Evento> eventosMarcados) {
        this.eventosMarcados = eventosMarcados;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void marcarEvento(String nomeEvento, String nome, Aplicativo app) {
        for(Evento e : app.listaDeEventos){
            if(nomeEvento.equals(e.nome)){
                for(Voluntario v : app.listaDeVoluntarios){
                    if (nome.equalsIgnoreCase(v.getNomeUsuario())) {
                        this.eventosMarcados.add(e);
                        System.out.println("Presença marcada com sucesso!");
                    }
                } 
            }
        }  
    }

    public static Voluntario logarVoluntario(Aplicativo app){
        int opcao=0;
        String organizador="";
        boolean usuarioLogado=false;

        if (usuarioLogado==false) {
            String nome = EntradaSaida.pedirDados("o nome de usuário para logar: ");
            while (app.procurarVoluntario(nome)==false) {
                System.out.print("Usuário não encontrado! Deseja se cadastrar ou tentar novamente? \n");
                opcao = EntradaSaida.pedirOpcao("1 - Cadastrar-se \n" +
                "2 - Tentar novamente\n");
                if (opcao == 1) {
                    Voluntario v = new Voluntario();
                    v.setSenha(EntradaSaida.pedirDados("o nome: "));
                    v.setNomeUsuario(EntradaSaida.pedirDados("o nome de usuário: "));
                    v.setEmail(EntradaSaida.pedirDados("o e-mail: "));
                    v.setSenha(EntradaSaida.pedirDados("a senha: "));
                    String senha = EntradaSaida.pedirDados("a senha novamente: ");
                    while (!senha.equals(v.getSenha())) {
                        System.out.println("As senhas não coincidem! ");
                        v.setSenha(EntradaSaida.pedirDados("senha: "));
                        senha = EntradaSaida.pedirDados("senha novamente: ");
                    }
                    app.adicionarVoluntario(v);
                    System.out.println("Usuário cadastrado com sucesso! ");
                } else {
                    nome = EntradaSaida.pedirDados("o nome de usuário novamente: ");
                    app.procurarVoluntario(nome);
                }
            }
            
            nome = EntradaSaida.pedirDados("o nome de usuário para logar: ");
            while (app.procurarVoluntario(nome)==false) {
                System.out.print("Usuário não encontrado! ");
                nome = EntradaSaida.pedirDados("o nome de usuário novamente: ");
                app.procurarVoluntario(nome);
            }
            
            while(app.verificarSeUsuarioLogado(nome)==false) {
                app.logarUsuario(nome);
            }
            System.out.println("Usuário logado com sucesso!");
            organizador = nome;
        }
        return v;
    }
    
    public static void chamarMetodos(Aplicativo app, String organizador, boolean usuarioLogado, Voluntario v) {
        //Aplicativo a = new Aplicativo();
        int opcao=0;
        int n=0;
        boolean empresaLogada=false;

        do{
            n = 6;
            opcao=0;
            if (usuarioLogado==true) {
                EntradaSaida.mostrarMenuVoluntário();
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
                        app.criarEvento(organizador);
                        break;
                    case 4:
                        String nomeEvento = EntradaSaida.pedirDados(" o nome do evento que deseja marcar presença: ");
                        v.marcarEvento(nomeEvento, v.getNomeUsuario(), app);
                        break;
                    case 5:
                        Empresa e = Empresa.logarEmpresa(app);
                        empresaLogada=true;
                        Empresa.chamarMetodos(app, empresaLogada, e);
                        break;
                    case 6:
                        usuarioLogado = false;
                        System.out.println("\n Deslogado com sucesso!");
                        break;
                    case 0:
                        System.exit(0);
                }

                if (opcao == 6) {
                    usuarioLogado=false;
                    break;
                }
            }
        } while (opcao!=0);
    }
}

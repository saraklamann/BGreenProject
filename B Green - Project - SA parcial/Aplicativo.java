import java.util.ArrayList;

public class Aplicativo {
    public ArrayList <Empresa> listaDeEmpresas = new ArrayList<Empresa>();
    public ArrayList <Evento> listaDeEventos = new ArrayList<Evento>();
    public ArrayList <Voluntario> listaDeVoluntarios = new ArrayList<Voluntario>();

    public void adicionarVoluntario(Voluntario v) {
        // DecimalFormat df = new DecimalFormat("###.###.###-##");
        // try {
            
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
        this.listaDeVoluntarios.add(v);
    }

    public boolean procurarVoluntario(String nome) {
        boolean usuarioCadastrado=false;

        for(Voluntario v : this.listaDeVoluntarios){
            if (nome.equalsIgnoreCase(v.getNomeUsuario())){
                usuarioCadastrado = true;
            }
        }
        return usuarioCadastrado;
    }

    public boolean verificarSeUsuarioLogado(String nome) {
        boolean usuarioLogado=false;
        for(Voluntario v : this.listaDeVoluntarios){
            if (nome.equalsIgnoreCase(v.getNomeUsuario()) ) {
                String senha = EntradaSaida.pedirDados("a senha: ");
                while (!senha.equals(v.getSenha())) {
                    System.out.print("Senha Inválida! ");
                    senha = EntradaSaida.pedirDados("a senha: ");
                } 
                usuarioLogado = true; 
            }
        }
        return usuarioLogado;
    }
    
    public boolean logarUsuario(String nome) {
        boolean usuarioLogado=false;
        for(Voluntario v : this.listaDeVoluntarios){
            if (nome.equalsIgnoreCase(v.getNomeUsuario()) ) {
                String senha = EntradaSaida.pedirDados("a senha: ");
                while (!senha.equals(v.getSenha())) {
                    System.out.print("Senha Inválida! ");
                    senha = EntradaSaida.pedirDados("a senha: ");
                } 
                usuarioLogado = true; 
            }
        }
        return usuarioLogado;
    }

    public String listarEmpresas(){ 
        String empresas= "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" +
                         "                                  \n" +
                         "      As empresas parceiras são:  \n\n" +
                         "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n";
        for(Empresa c : this.listaDeEmpresas){
            if (!listaDeEmpresas.isEmpty()){
                empresas += c.getNome() + "\n" +
                "POSTOS DE COLETA:\n";
                for(Postos p: c.listaDePostosDeColeta){
                    if (p.empresa.getCnpj().equals(c.getCnpj())) {
                        empresas += p.rua + ", " + p.numero  + " - " + p.cidade + "\n\n";  
                    }
                }
            }
            empresas += "PARA ONDE VAI ESTE MATERIAL?\n" +
            c.getDescricao() + "\n\n"+
            "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n";                
        }
        return empresas;
    }

    public String listarEventos(){
        String eventos = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" +
                         "                                  \n"   +
                         "    Os eventos em andamento são:  \n\n" +
                         "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n";
        boolean haEventos=false;
        for(Evento e : this.listaDeEventos){
            if (!listaDeEventos.isEmpty()) {
                haEventos = true;
                e.nome = e.nome.toUpperCase();
                eventos += e.nome + "\n\n"+
                           "- Data do evento: " + e.data + "\n" +
                           "- Endereço do evento: " + e.endereco + "\n" +
                           "- Organizador do evento: " + e.organizador + "\n" +
                           "- Junte-se aos outros n voluntarios neste evento!"+ "\n\n" +
                           "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n"; 
            }
        }
        if (haEventos == false) {
            return eventos += "    Não há eventos programados :c";
        } else {
            return eventos;
        }
    }

    public void criarEvento(String organizador) {
        Evento e = new Evento();
        e.nome = EntradaSaida.pedirDados("o nome do evento: ");
        e.data = EntradaSaida.pedirDados("o data do evento: ");
        e.endereco = EntradaSaida.pedirDados("o endereço do evento: ");
        e.organizador = organizador;

        this.listaDeEventos.add(e); 
    }

    public boolean procurarEmpresa(String cnpj) {
        boolean empresaCadastrada=false;
        for(Empresa empresa : this.listaDeEmpresas){
            if (cnpj.equals(empresa.getCnpj())) {
                empresaCadastrada=true;
            }
        }
        return empresaCadastrada;
    }

    public boolean verificarSeEmpresaLogada(String cnpj) {
        boolean empresaLogada=false;
        for(Empresa empresa: this.listaDeEmpresas){
             if(cnpj.equals(empresa.getCnpj())){
                empresaLogada = this.logarEmpresa(cnpj);
        //         String senha = EntradaSaida.pedirDados("a senha: ");
        //         while(!senha.equals(empresa.getSenha())){
        //             System.out.println("Senha Inválida! ");
        //             senha = EntradaSaida.pedirDados("a senha: ");
        //         }
        //         empresaLogada = true;
             }
        }
        return empresaLogada;
    }

    public boolean logarEmpresa(String cnpj) {
        boolean empresaLogada=false;
        for(Empresa empresa: this.listaDeEmpresas){
            if(cnpj.equals(empresa.getCnpj())){
                String senha = EntradaSaida.pedirDados("a senha: ");
                while(!senha.equals(empresa.getSenha())){
                    System.out.println("Senha Inválida! ");
                    senha = EntradaSaida.pedirDados("a senha: ");
                }
                empresaLogada = true;
            }
        }
        return empresaLogada;
    }

    public void adicionarEmpresa(Empresa empresa) {
        this.listaDeEmpresas.add(empresa);
    }

    public void editarDescricao(String cnpj) {
        for(Empresa e: this.listaDeEmpresas){
            if (cnpj.equals(e.getCnpj())) {
                e.setDescricao(EntradaSaida.pedirDados("Para onde vai o material coletado: "));
            }
        }
    }

    public void editarPosto(String cnpj) {
        for(Empresa e : this.listaDeEmpresas){
            if (cnpj.equals(e.getCnpj())) {
                String rua = EntradaSaida.pedirDados("a rua do endereço a ser alterado: ");
                String numero = EntradaSaida.pedirDados("o numero do endereço a ser alterado: ");
                for(Postos p : e.listaDePostosDeColeta){
                    if (numero.equalsIgnoreCase(p.numero) && rua.equals(p.rua)) {
                        p.cidade = EntradaSaida.pedirDados("a nova cidade em que se encontra o posto de coleta: ");
                        p.rua = EntradaSaida.pedirDados("a nova rua em que se encontra o posto de coleta: ");
                        p.numero = EntradaSaida.pedirDados("o novo numero em que se encontra o posto de coleta: ");
                    } else {
                        System.out.println("Posto não encontrado! ");
                    }
                }
            }
        }
    }

}
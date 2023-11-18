import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Programa {
	
	static Scanner scanner = new Scanner(System.in);
	static File pasta = new File("C:\\Users\\laboratorioa\\Desktop\\Notes");

	public static void main(String[] args) {
		if(!pasta.exists()) {
			System.out.println("Deseja criar a pasta 'Notes' em 'C:\\Users\\laboratorioa\\Desktop'? S ou N:");
			
			String opcao = scanner.nextLine();
			if(opcao.equals("S")) {
				pasta.mkdir();
				System.out.println("Pasta criada!");
			}else {
				System.out.println("Encerrando...");
				return;
			}
		}
		
		int opcao = -1;
		
		do {
			System.out.println("Digite: ");
			System.out.println("1 - Listar arquivos e pastas");
			System.out.println("2 - Criar arquivo");
			System.out.println("3 - Ler arquivo");
			System.out.println("4 - Escrever no arquivo");
			System.out.println("5 - Deletar arquivo");
			System.out.println("0 - Sair");
			
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
				case 1:
					listarItens();
					break;
				case 2:
					criarArquivo();
					break;
				case 3:
					lerArquivo();
					break;
				case 4:
					escreverNoArquivo();
					break;
				case 5:
					deletarArquivo();
					break;
				default:
					System.out.println("Opção inválida.");
			}
			
		}while(opcao != 0);
		
	}

	private static void deletarArquivo() {
		listarArquivos();
		
		System.out.println("Qual arquivo deseja deletar?");
		int opcao = scanner.nextInt();
		scanner.nextLine();
		
		File [] files = pasta.listFiles();
		
		try {
			if(files[opcao].isFile()) {
				files[opcao].delete();
				System.out.println("Arquivo apagado com sucesso!");
			}
			else
				System.out.println("O item selecionado é uma pasta.");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("A opção selecionada está fora do intervalo.");
		}catch(Exception e) {
			System.out.println("O arquivo não pode ser excluído: " + e.getMessage());
		}
		
	}

	private static void escreverNoArquivo() {
		listarArquivos();
		
		System.out.println("Em qual arquivo deseja escrever?");
		int opcao = scanner.nextInt();
		scanner.nextLine();
		
		File [] files = pasta.listFiles();
		File file = files[opcao];
		
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			System.out.println("Escreva a vontade e digite '0' para finalizar:");
			String linha = "";
			
			while(true) {
				linha = scanner.nextLine();
				
				if(linha.equals("0")) {
					break;
				}
				
				printWriter.println(linha);
			}
			
			printWriter.flush();
			printWriter.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		
	}

	private static void lerArquivo() {
		listarArquivos();
		
		System.out.println("Qual arquivo deseja ler?");
		int opcao = scanner.nextInt();
		scanner.nextLine();
		
		File [] files = pasta.listFiles();
		File file = files[opcao];
		
		try {
			Scanner leitorArquivo = new Scanner(file);
			
			while( leitorArquivo.hasNextLine() ) {
				System.out.println( leitorArquivo.nextLine() );
			}
			
			System.out.println(" # Fim # ");
		}catch(Exception e) {
			System.out.println("Erro ao ler arquivo: " + e.getMessage());
		}
		
	}

	private static void criarArquivo(){
		System.out.println("Digite o nome do arquivo: ");
		String nome = scanner.nextLine();
		
		File arquivo = new File(pasta, nome);
		
		if( arquivo.exists() ) {
			System.out.println("Arquivo já existe!");
		}else{
			try {				
				boolean result = arquivo.createNewFile();
				if(result) {
					System.out.println("Arquivo criado com sucesso!");
				}
				
			}catch(Exception erro) {
				System.out.println("Erro ao criar arquivo: " + erro.getMessage());
			}
			
		}
		
	}

	private static void listarArquivos() {
		File[] itens = pasta.listFiles();
		
		for(int i=0; i<itens.length; i++) {
			if(itens[i].isFile())
				System.out.println("\t" + i + " - " + itens[i].getName());
		}
	}
	
	private static void listarPastas() {
		File[] itens = pasta.listFiles();
		
		for(int i=0; i<itens.length; i++) {
			if(itens[i].isDirectory())
				System.out.println("\t" + i + " - " + itens[i].getName());
		}
	}
	
	private static void listarItens() {
		System.out.println("Pastas:");
		listarPastas();
		
		System.out.println("\nArquivos:");
		listarArquivos();
		
		System.out.println("\n");
	}

}

package main;

//*** CODE DEVELOPED BY: MAIQUE DOGLAS MORAES DA SILVA ***

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int tempoMaximoConclusao = 8;
		String dataInicial = "2019-11-10 09:00:00";
		String dataFinal = "2019-11-11 12:00:00";
		
//Criação do array e inserção dos dados.
		List<Trabalho> trabalhos = new ArrayList<>() {
			{
				add(new Trabalho(1, "Importação de arquivos de fundos", "2019-11-10 12:00:00", 2));
				add(new Trabalho(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4));
				add(new Trabalho(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6));
			}
		};

		List<Trabalho> filtraTrabalhos = new Trabalho().filtersTrabalhos(trabalhos, dataInicial, dataFinal);

		int[][] trabalhosIds = new Trabalho().selectTrabalhos(filtraTrabalhos, tempoMaximoConclusao);

		System.out.println(Arrays.deepToString(trabalhosIds));

	}

}
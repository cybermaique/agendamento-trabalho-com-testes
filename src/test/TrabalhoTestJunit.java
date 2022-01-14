package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.Trabalho;

public class TrabalhoTestJunit {
	
//Testa o limite de trabalho setado em 8hs, mantendo o trabalho ID 1 e 3 no mesmo array (totalizando 8hs) e o trabalho ID 2 no outro array (totalizando 4hs).
	@Test
	public void testTrabalhos() {
		String dataInicial = "2019-11-10 09:00:00";
		String dataFinal = "2019-11-11 12:00:00";
		int tempoMaximoConclusao = 8;

		List<Trabalho> trabalhos = new ArrayList<>() {
			{
				add(new Trabalho(1, "Importação de arquivos de fundos", "2019-11-10 12:00:00", 2));
				add(new Trabalho(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4));
				add(new Trabalho(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6));
			}
		};

		List<Trabalho> filtraTrabalhos = new Trabalho().filtersTrabalhos(trabalhos, dataInicial, dataFinal);

		int[][] trabalhosIds = new Trabalho().selectTrabalhos(filtraTrabalhos, tempoMaximoConclusao);

		Assertions.assertEquals(Arrays.deepToString(trabalhosIds), "[[1, 3], [2]]");
	}

	
//Testa o limite de trabalho setado em 12hs, mantendo os 3 trabalhos no mesmo array.
	@Test
	public void testTempoConclusao12Horas() {
		String dataInicial = "2019-11-10 09:00:00";
		String dataFinal = "2019-11-11 12:00:00";
		int tempoMaximoConclusao = 12;

		List<Trabalho> trabalhos = new ArrayList<>() {
			{
				add(new Trabalho(1, "Importação de arquivos de fundos", "2019-11-10 12:00:00", 2));
				add(new Trabalho(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4));
				add(new Trabalho(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6));
			}
		};

		List<Trabalho> filtraTrabalhos = new Trabalho().filtersTrabalhos(trabalhos, dataInicial, dataFinal);

		int[][] trabalhosIds = new Trabalho().selectTrabalhos(filtraTrabalhos, tempoMaximoConclusao);

		Assertions.assertEquals(Arrays.deepToString(trabalhosIds), "[[1, 3, 2]]");
	}

//Testa se os trabalhos são executados fora da faixa da janela de execução.
	@Test
	public void testTrabalhosForaDoIntervaloDeDatas() {
		String dataInicial = "2029-11-10 09:00:00";
		String dataFinal = "2029-11-11 12:00:00";
		int tempoMaximoConclusao = 8;

		List<Trabalho> trabalhos = new ArrayList<>() {
			{
				add(new Trabalho(1, "Importação de arquivos de fundos", "2019-11-10 12:00:00", 2));
				add(new Trabalho(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4));
				add(new Trabalho(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6));
			}
		};

		List<Trabalho> filteredTrabalhos = new Trabalho().filtersTrabalhos(trabalhos, dataInicial, dataFinal);

		Assertions.assertTrue(filteredTrabalhos.isEmpty());
	}

//Testa se os trabalhos são executados apenas dentro da faixa da janela de execução.
	@Test
	public void testTrabalhosDentroDoIntervaloDeDatas() {
		String dataInicial = "2019-11-10 09:00:00";
		String dataFinal = "2019-11-11 12:00:00";
		int tempoMaximoConclusao = 8;

		List<Trabalho> trabalhos = new ArrayList<>() {
			{
				add(new Trabalho(1, "Importação de arquivos de fundos", "2019-11-10 12:00:00", 2));
				add(new Trabalho(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4));
				add(new Trabalho(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6));
			}
		};

		List<Trabalho> filtraTrabalhos = new Trabalho().filtersTrabalhos(trabalhos, dataInicial, dataFinal);

		Assertions.assertEquals(filtraTrabalhos.size(), 3);
	}
}
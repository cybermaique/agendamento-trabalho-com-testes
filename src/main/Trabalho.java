package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Trabalho {
	private int id;
	private String descricao;
	private LocalDateTime dataMaxima;
	private int tempoEstimado;

//Construtores
	public Trabalho() {
	}

	public Trabalho(int id, String descricao, String dataMaxima, int tempoEstimado) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataMaxima = LocalDateTime.parse(dataMaxima, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.tempoEstimado = tempoEstimado;
	}
	
//Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataMaxima() {
		return dataMaxima;
	}

	public void setDataMaxima(LocalDateTime dataMaxima) {
		this.dataMaxima = dataMaxima;
	}

	public int getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(int tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

//Retorno String do objeto
	@Override
	public String toString() {
		return "Trabalho [id=" + id + ", descricao=" + descricao + ", dataMaxima=" + dataMaxima + ", tempoEstimado="
				+ tempoEstimado + "]";
	}

//Stream Filter com Lambda utilizado para verificar se as datas de trabalho estão dentro do range da Janela de Execução.
	public List<Trabalho> filtersTrabalhos(List<Trabalho> trabalhos, String dataInicial, String dataFinal) {
		LocalDateTime inicio = LocalDateTime.parse(dataInicial, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime conclusao = LocalDateTime.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		List<Trabalho> filtraTrabalhos = trabalhos.stream().filter(
				trabalho -> (trabalho.getDataMaxima().isEqual(inicio) || trabalho.getDataMaxima().isAfter(inicio))	&& (trabalho.getDataMaxima().isBefore(conclusao)
				|| trabalho.getDataMaxima().isEqual(conclusao)))
				.sorted(Comparator.comparing(Trabalho::getDataMaxima)).collect(Collectors.toList());

		return filtraTrabalhos;
	}

	public int[][] selectTrabalhos(List<Trabalho> trabalhos, int tempoMaximoConclusao) {
		List<int[]> trabalhosIds = new ArrayList<>();
		for (int i = 0; i < trabalhos.size(); i++) {
			int tempoTotalConclusao = 0;
			int z = -1;

//Calcula tempo restante de trabalho, para não inserir novos trabalhos em caso de exceder 8hs.
			for (z = i; z < trabalhos.size() && tempoTotalConclusao
					+ trabalhos.get(z).getTempoEstimado() <= tempoMaximoConclusao; tempoTotalConclusao += trabalhos.get(z++).getTempoEstimado()) {
			}

			List<Trabalho> groupTrabalhos = trabalhos.subList(i, z);

//Garante que o último registro não seja inserido, caso ultrapasse as 8hs.
			i = --z;

			trabalhosIds.add(groupTrabalhos.stream().mapToInt(Trabalho::getId).toArray());
		}

		return trabalhosIds.toArray(new int[0][]);
	}

}
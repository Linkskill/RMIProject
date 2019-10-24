package Models;

/**
 * Enumeração dos tipos de eventos (passagens, hospedagens ou pacotes).
 */
public enum EventType {
	NEW_TICKET("novas passagens"),
	NEW_ACCOMMODATION("novas hospedagens"),
	NEW_PACKAGE("novos pacotes");

	private String name;

    /**
     * Construtor de EventType que define o tipo do evento.
     * @param name nome do evento do tipo String
     */
	EventType(String name) {
		this.name = name;
	}

    /**
     * Retorna o nome do evento.
     * @return name nome do evento do tipo String
     */
	@Override
	public String toString() {
		return name;
	}
}

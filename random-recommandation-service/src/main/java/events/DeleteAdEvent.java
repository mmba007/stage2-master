package events;

public class DeleteAdEvent extends Event {

	private String id;

	public DeleteAdEvent() {
		super();
	}

	public DeleteAdEvent(String id) {
		super(EventName.DELETE_AD);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

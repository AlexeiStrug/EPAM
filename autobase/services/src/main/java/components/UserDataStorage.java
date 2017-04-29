package components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request")
public class UserDataStorage {

	private boolean isLoggedIn;

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	@Override
	public String toString() {
		return "UserDataStorage [isLoggedIn=" + isLoggedIn + ";hashCode=" + hashCode() + "]";
	}
}

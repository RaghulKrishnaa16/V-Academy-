
package vacademy.model;


public enum RoleEnum {
	Owner("Owner", 1), Administrator("Administrator", 2), Editor("Editor", 3), Viewer("Viewer",
			4), Commentor("Commentor", 5);

	private String role;
	private int roleId;

	private RoleEnum(String role, int roleId) {
		this.role = role;
		this.roleId = roleId;
	}

	public static RoleEnum fromValues(String givenRole) {
		for (RoleEnum role : values()) {
			if (role.role.equals(givenRole)) {
				return role;
			}
		}

		throw new IllegalArgumentException(givenRole + " not found");
	}

	
	public String getRole() {
		return role;
	}

		public int getRoleId() {
		return roleId;
	}
}

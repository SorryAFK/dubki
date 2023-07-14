package com.pzobenko.petproject.demo.domain.enums;

public enum Role {
  USER(false,false),
  ADMIN(true, false),
  HR(false, true),
  OWNER(true, true);
  private final boolean canModeratingContent;
  private final boolean canGiveRolesToOthers;

  Role(boolean canModeratingContent, boolean canGiveRolesToOthers) {
    this.canModeratingContent = canModeratingContent;
    this.canGiveRolesToOthers = canGiveRolesToOthers;
  }

  public boolean isCanModeratingContent() {
    return canModeratingContent;
  }

  public boolean isCanGiveRolesToOthers() {
    return canGiveRolesToOthers;
  }
}

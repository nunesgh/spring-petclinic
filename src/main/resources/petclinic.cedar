// Admins can do everything.
permit(
    principal == PetClinic::Role::"ADMIN",
    action,
    resource
);

// Users can only "view".
permit(
    principal == PetClinic::Role::"USER",
    action == PetClinic::Action::"view",
    resource
);

// Guests are forbidden from editing.
forbid(
    principal == PetClinic::Role::"GUEST",
    action == PetClinic::Action::"edit",
    resource
);

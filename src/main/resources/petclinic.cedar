// Admins can do everything.
permit(
    principal == PetClinic::Role::"ADMIN",
    action,
    resource
);

// Vets can "view" and "edit" everything.
permit(
    principal == PetClinic::Role::"VET",
    action == PetClinic::Action::"preview",
    resource
);
permit(
    principal == PetClinic::Role::"VET",
    action == PetClinic::Action::"view",
    resource
);
permit(
    principal == PetClinic::Role::"VET",
    action == PetClinic::Action::"edit",
    resource
);

// Owners can only "view" themselves.
permit(
    principal == PetClinic::Role::"OWNER",
    action == PetClinic::Action::"preview",
    resource == PetClinic::Database::"owners"
);
permit(
    principal == PetClinic::Role::"OWNER",
    action == PetClinic::Action::"view",
    resource == PetClinic::Database::"owners"
) when {
    context.currentUser == context.currentOwner
};

// Guests are forbidden from viewing and editing.
forbid(
    principal == PetClinic::Role::"GUEST",
    action == PetClinic::Action::"view",
    resource
);
forbid(
    principal == PetClinic::Role::"GUEST",
    action == PetClinic::Action::"edit",
    resource
);

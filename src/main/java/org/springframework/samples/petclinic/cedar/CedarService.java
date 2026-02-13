package org.springframework.samples.petclinic.cedar;

import com.cedarpolicy.BasicAuthorizationEngine;
import com.cedarpolicy.model.AuthorizationRequest;
import com.cedarpolicy.model.AuthorizationResponse;
import com.cedarpolicy.model.policy.PolicySet;
import com.cedarpolicy.value.EntityUID;
import com.cedarpolicy.model.Context;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;

@Service
public class CedarService {

	private final BasicAuthorizationEngine engine;

	private final PolicySet policySet;

	public CedarService() {
		try {
			String policyText = Files.readString(Path.of("src/main/resources/petclinic.cedar"));
			this.policySet = PolicySet.parsePolicies(policyText);
			this.engine = new BasicAuthorizationEngine();
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to initialize Cedar Engine", e);
		}
	}

	public boolean checkAccess(String userRole, String actionType, String resourceId) {
		Context context = new Context(Collections.emptyMap());

		AuthorizationRequest request = new AuthorizationRequest(
				EntityUID.parse("PetClinic::Role::\"" + userRole + "\"").get(),
				EntityUID.parse("PetClinic::Action::\"" + actionType + "\"").get(),
				EntityUID.parse("PetClinic::Database::\"" + resourceId + "\"").get(), context);

		try {
			AuthorizationResponse response = engine.isAuthorized(request, this.policySet, new HashSet<>());
			return response.toString().contains("Allow");
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

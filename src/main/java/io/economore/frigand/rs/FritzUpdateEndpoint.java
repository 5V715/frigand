package io.economore.frigand.rs;

import io.economore.frigand.gandi.GandiAUpdateRequest;
import io.economore.frigand.gandi.GandiAnswer;
import io.economore.frigand.gandi.GandiClient;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/update")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FritzUpdateEndpoint {

    @NonNull
    private GandiClient client;

    @Path("/")
    @GET
    @Produces("text/plain")
    public Response update(@QueryParam("ipaddr") String ipaddr, @QueryParam("username") String username, @QueryParam("pass") String pass,
                           @QueryParam("domain") String domain, @QueryParam("ip6addr") String ip6ddr) {
        GandiAUpdateRequest request = new GandiAUpdateRequest();
        request.ip(ipaddr);
        GandiAnswer answer = client.update(domain, "@", "A", username, request);
        if("DNS Record Created".equals(answer.getMessage())) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}

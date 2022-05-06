package me.kyeongho.userservice.error;

import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final Environment env;

    @Override
    public Exception decode(String s, Response response) {
        int status = response.status();
        Request request = response.request();
        byte[] body = new byte[0];
        try {
            body = response.body().asInputStream().readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = s + ", " + new String(body, StandardCharsets.UTF_8);

        switch(status) {
            case 400:
                return new FeignException.BadRequest(message, request, body);
            case 401:
                return new FeignException.Unauthorized(message, request, body);
            case 402:
            case 407:
            case 408:
            case 411:
            case 412:
            case 413:
            case 414:
            case 416:
            case 417:
            case 418:
            case 419:
            case 420:
            case 421:
            case 423:
            case 424:
            case 425:
            case 426:
            case 427:
            case 428:
            case 403:
                return new FeignException.Forbidden(message, request, body);
            case 404:
                if (s.contains("getOrders")) {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty("order-service.exception.order-is-empty"));
                }
                return new FeignException.NotFound(message, request, body);
            case 405:
                return new FeignException.MethodNotAllowed(message, request, body);
            case 406:
                return new FeignException.NotAcceptable(message, request, body);
            case 409:
                return new FeignException.Conflict(message, request, body);
            case 410:
                return new FeignException.Gone(message, request, body);
            case 415:
                return new FeignException.UnsupportedMediaType(message, request, body);
            case 422:
                return new FeignException.UnprocessableEntity(message, request, body);
            case 429:
                return new FeignException.TooManyRequests(message, request, body);
            case 500:
                return new FeignException.InternalServerError(message, request, body);
            case 501:
                return new FeignException.NotImplemented(message, request, body);
            case 502:
                return new FeignException.BadGateway(message, request, body);
            case 503:
                return new FeignException.ServiceUnavailable(message, request, body);
            case 504:
                return new FeignException.GatewayTimeout(message, request, body);
            default:
                return new FeignException.FeignServerException(status, message, request, body);
        }
    }
}

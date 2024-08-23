package org.poc.onlinemovieticket.services.impls;

import org.poc.onlinemovieticket.entities.Theatre;
import org.poc.onlinemovieticket.repositories.TheatreRepository;
import org.poc.onlinemovieticket.services.TheatreService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class TheatreServiceImpl implements TheatreService {

    TheatreRepository theatreRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository){
        this.theatreRepository = theatreRepository;
    }

}

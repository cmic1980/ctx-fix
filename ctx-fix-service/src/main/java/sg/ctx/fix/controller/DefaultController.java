package sg.ctx.fix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sg.ctx.fix.model.response.NullResponse;

/**
 * @author yu.miao
 */
@RestController
public class DefaultController {

    @GetMapping("/index")
    @ResponseBody
    public ResponseEntity<?> index() {
        NullResponse nullResponse = new NullResponse();
        nullResponse.setResult(true);
        return ResponseEntity.ok().body(nullResponse);
    }
}

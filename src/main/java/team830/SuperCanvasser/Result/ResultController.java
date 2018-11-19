package team830.SuperCanvasser.Result;

 import org.bson.types.ObjectId;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpRequest;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
 import team830.SuperCanvasser.Campaign.Campaign;
 import team830.SuperCanvasser.SuperCanvasserApplication;
 import team830.SuperCanvasser.Task.Task;
 import team830.SuperCanvasser.Task.TaskService;

 import javax.servlet.http.HttpServletRequest;
 import javax.xml.ws.Response;
 import java.util.List;
 import java.util.ResourceBundle;

@RequestMapping("/manager/result")
@RestController
public class ResultController {
    private static final Logger log = LoggerFactory.getLogger(SuperCanvasserApplication.class);

    @Autowired
    private ResultService resultService;
    @Autowired
    private TaskService taskService;

    // TableView Result @RequestParam = campaign id RETURN = list of tasks
    @RequestMapping(value = "/tableView", method = RequestMethod.POST)
    public ResponseEntity tableViewResult(@RequestBody Campaign campaign, BindingResult result){
        if(result.hasErrors()){
            log.info("ResultController :: Failed to find tasks");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request Failed");
        }
        // Find all the task by ID
        return ResponseEntity.ok(taskService.findAllTasksById(campaign.getTasks()));
    }

    @RequestMapping(value = "/statView", method = RequestMethod.POST)
    public ResponseEntity statViewResult(@RequestBody Campaign campaign, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("ResultController :: Failed to gather Result");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request Failed");
        }
        List<Task> tasks = taskService.findAllTasksById(campaign.getTasks());
        // create and save result
        Result result = new Result(ObjectId.get().toHexString(), campaign, taskService.getAllRatings(tasks));
        log.info("ResultController :: Result for StatView");
        return ResponseEntity.ok(resultService.createResult(result));
    }

    @RequestMapping(value = "/mapView", method = RequestMethod.POST)
    public ResponseEntity mapViewResult(@RequestBody Campaign campaign, HttpServletRequest request){
        Result result = resultService.findByCampaignId(campaign.get_id());
        if(result==null){
            log.info("ResultController :: Failed to gather Result");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request Failed");
        }
        log.info("ResultController :: Ratings for MapView");
        return ResponseEntity.ok(result.getRatings());

    }

}

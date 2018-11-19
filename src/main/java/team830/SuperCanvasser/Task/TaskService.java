package team830.SuperCanvasser.Task;

import com.google.common.primitives.Doubles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team830.SuperCanvasser.Location.Location;
import team830.SuperCanvasser.Location.LocationRepo;
import team830.SuperCanvasser.Status;
import team830.SuperCanvasser.SuperCanvasserApplication;
import team830.SuperCanvasser.User.User;
import team830.SuperCanvasser.User.UserRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class TaskService implements TaskInterface {

    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;

    private static final Logger log = LoggerFactory.getLogger(SuperCanvasserApplication.class);

    @Override
    public Task editTask(Task task) {
        log.debug("Executing edit task - service");
        if (task.get_id() != null) {
            if (taskRepo.existsById(task.get_id())) {
                log.debug("Task Exists");
                return taskRepo.save(task);
            }
        } else {
            log.debug("Task doesn't exist");
        }
        return null;
    }

    @Override
    public Task addTask(Task task) {
        log.info("TaskService :: Executing add task - service");
        return taskRepo.insert(task);
    }

    @Override
    public Task findBy_Id(String id) {
        log.debug("Executing find task by id - service");
        return taskRepo.findBy_id(id);
    }

    @Override
    public List<Task> findByCanvasserIdAndTaskStatus(String id, Status status) {
        log.debug("Executing find tasks by canvasser ID - service");
        return taskRepo.findByCanvasserIdAndTaskStatus(id, status);
    }

    @Override
    public Task findTodayTask(String _id){
        List<Task> tasks = taskRepo.findAll();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        for(Task t : tasks){
            if(t.getCanvasserId().equals(_id) && t.getDate().equals(formatter.format(date).toString()))
                return t;
        }
        return null;
    }

    @Override
    public List<Task> findAllTasksById(List<String> ts){
        List<Task> tasks = new ArrayList<>();
        for(String t : ts){
            tasks.add(taskRepo.findBy_id(t));
        }
        return tasks;
    }

    @Override
    public List<Location> findLocationsById(List<String> locs){
        List<Location> locations = new ArrayList<>();
        for(String loc : locs){
            locations.add(locationRepo.findLocationBy_id(loc));
        }
        return locations;
    }

    // for view task.. returns the user id and gets the user
    public User getCanvasserById(String _id){
        log.info("TaskService :: getting canvasser by id");
        return userRepo.findBy_id(_id);
    }

    public double [] getAllRatings(List<Task> tasks){
        List<Double> ratings = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++){
            List<Location> locations = findLocationsById(tasks.get(i).getLocations());
            for(int k = 0; k < locations.size(); k++){
                ratings.add((double)locations.get(k).getRating());
            }
        }
        return Doubles.toArray(ratings);
    }

}

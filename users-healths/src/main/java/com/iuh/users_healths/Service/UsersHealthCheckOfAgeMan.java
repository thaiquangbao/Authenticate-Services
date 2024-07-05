package com.iuh.users_healths.Service;

import com.iuh.users_healths.Dtos.HealthDto;
import com.iuh.users_healths.Dtos.Reponse.Users_Healths_Response;
import com.iuh.users_healths.Dtos.Resquest.Users_Healths_Request;
import com.iuh.users_healths.Mappers.UsersMappers;
import com.iuh.users_healths.Mappers.Users_Health_Mapper;
import com.iuh.users_healths.Models.Users;
import com.iuh.users_healths.Models.Users_Health;
import com.iuh.users_healths.Repositories.Users_Health_Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersHealthCheckOfAgeMan {
    @Autowired
    private Healths_Status_Res healthsStatusRes;
    @Autowired
    private Users_Health_Repositories usersHealthRepositories;
    @Autowired
    private Users_Health_Mapper usersHealthMapper;
    @Autowired
    private UsersMappers usersMapper;
    public Users_Healths_Response age15To19(HealthDto healthDto, Users users) {
        int bloodPressureConvert = Integer.parseInt(healthDto.getBloodPressure()) ;
        int heartbeatConvert = Integer.parseInt(healthDto.getHeartbeat()) ;
        if ((bloodPressureConvert >= 70 && bloodPressureConvert <= 120) && (heartbeatConvert >= 52 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 70) && (heartbeatConvert >= 52 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 120) && (heartbeatConvert >= 52 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 70 && bloodPressureConvert <= 120) && (heartbeatConvert < 52)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 70 && bloodPressureConvert <= 120) && (heartbeatConvert > 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
    public Users_Healths_Response age20To29(HealthDto healthDto, Users users) {
        int bloodPressureConvert = Integer.parseInt(healthDto.getBloodPressure()) ;
        int heartbeatConvert = Integer.parseInt(healthDto.getHeartbeat()) ;
        if ((bloodPressureConvert >= 75 && bloodPressureConvert <= 124) && (heartbeatConvert >= 52 && heartbeatConvert <= 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 75) && (heartbeatConvert >= 52 && heartbeatConvert <= 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 124) && (heartbeatConvert >= 52 && heartbeatConvert <= 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 75 && bloodPressureConvert <= 124) && (heartbeatConvert < 52)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 75 && bloodPressureConvert <= 124) && (heartbeatConvert > 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
    public Users_Healths_Response age30To39(HealthDto healthDto, Users users) {
        int bloodPressureConvert = Integer.parseInt(healthDto.getBloodPressure()) ;
        int heartbeatConvert = Integer.parseInt(healthDto.getHeartbeat()) ;
        if ((bloodPressureConvert >= 79 && bloodPressureConvert <= 126) && (heartbeatConvert >= 52 && heartbeatConvert <= 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 79) && (heartbeatConvert >= 52 && heartbeatConvert <= 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 126) && (heartbeatConvert >= 52 && heartbeatConvert <= 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 79 && bloodPressureConvert <= 126) && (heartbeatConvert < 52)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 79 && bloodPressureConvert <= 126) && (heartbeatConvert > 89)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
    public Users_Healths_Response age40To49(HealthDto healthDto, Users users) {
        int bloodPressureConvert = Integer.parseInt(healthDto.getBloodPressure()) ;
        int heartbeatConvert = Integer.parseInt(healthDto.getHeartbeat()) ;
        if ((bloodPressureConvert >= 83 && bloodPressureConvert <= 130) && (heartbeatConvert >= 52 && heartbeatConvert <= 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 83) && (heartbeatConvert >= 52 && heartbeatConvert <= 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 130) && (heartbeatConvert >= 52 && heartbeatConvert <= 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 83 && bloodPressureConvert <= 130) && (heartbeatConvert < 52)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 83 && bloodPressureConvert <= 130) && (heartbeatConvert > 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
    public Users_Healths_Response age50To59(HealthDto healthDto, Users users) {
        int bloodPressureConvert = Integer.parseInt(healthDto.getBloodPressure()) ;
        int heartbeatConvert = Integer.parseInt(healthDto.getHeartbeat()) ;
        if ((bloodPressureConvert >= 85 && bloodPressureConvert <= 137) && (heartbeatConvert >= 52 && heartbeatConvert <= 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 85) && (heartbeatConvert >= 52 && heartbeatConvert <= 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 137) && (heartbeatConvert >= 52 && heartbeatConvert <= 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 85 && bloodPressureConvert <= 137) && (heartbeatConvert < 52)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 85 && bloodPressureConvert <= 137) && (heartbeatConvert > 90)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
    public Users_Healths_Response age60To69(HealthDto healthDto, Users users) {
        int bloodPressureConvert = Integer.parseInt(healthDto.getBloodPressure()) ;
        int heartbeatConvert = Integer.parseInt(healthDto.getHeartbeat()) ;
        if ((bloodPressureConvert >= 84 && bloodPressureConvert <= 143) && (heartbeatConvert >= 50 && heartbeatConvert <= 91)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 84) && (heartbeatConvert >= 50 && heartbeatConvert <= 91)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 143) && (heartbeatConvert >= 50 && heartbeatConvert <= 91)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 84 && bloodPressureConvert <= 143) && (heartbeatConvert < 50)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 84 && bloodPressureConvert <= 143) && (heartbeatConvert > 91)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
    public Users_Healths_Response age70Up(HealthDto healthDto, Users users) {
        int bloodPressureConvert = Integer.parseInt(healthDto.getBloodPressure()) ;
        int heartbeatConvert = Integer.parseInt(healthDto.getHeartbeat()) ;
        if ((bloodPressureConvert >= 82 && bloodPressureConvert <= 145) && (heartbeatConvert >= 51 && heartbeatConvert <= 94)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 82) && (heartbeatConvert >= 51 && heartbeatConvert <= 94)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 145) && (heartbeatConvert >= 51 && heartbeatConvert <= 94)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 82 && bloodPressureConvert <= 145) && (heartbeatConvert < 51)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 82 && bloodPressureConvert <= 145) && (heartbeatConvert > 94)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
}

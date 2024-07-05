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
public class UsersHealthCheckOfAgeWoman {
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
        if ((bloodPressureConvert >= 67 && bloodPressureConvert <= 111) && (heartbeatConvert >= 58 && heartbeatConvert <= 99)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 67) && (heartbeatConvert >= 58 && heartbeatConvert <= 99)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 111) && (heartbeatConvert >= 58 && heartbeatConvert <= 99)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 67 && bloodPressureConvert <= 111) && (heartbeatConvert < 58)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 67 && bloodPressureConvert <= 111) && (heartbeatConvert > 99)) {
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
        if ((bloodPressureConvert >= 69 && bloodPressureConvert <= 114) && (heartbeatConvert >= 57 && heartbeatConvert <= 95)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 69) && (heartbeatConvert >= 57 && heartbeatConvert <= 95)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 114) && (heartbeatConvert >= 57 && heartbeatConvert <= 95)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 69 && bloodPressureConvert <= 114) && (heartbeatConvert < 57)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 69 && bloodPressureConvert <= 114) && (heartbeatConvert > 95)) {
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
        if ((bloodPressureConvert >= 73 && bloodPressureConvert <= 118) && (heartbeatConvert >= 57 && heartbeatConvert <= 95)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 73) && (heartbeatConvert >= 57 && heartbeatConvert <= 95)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 118) && (heartbeatConvert >= 57 && heartbeatConvert <= 95)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 73 && bloodPressureConvert <= 118) && (heartbeatConvert < 57)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 73 && bloodPressureConvert <= 118) && (heartbeatConvert > 95)) {
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
        if ((bloodPressureConvert >= 78 && bloodPressureConvert <= 126) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 78) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 126) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 78 && bloodPressureConvert <= 126) && (heartbeatConvert < 56)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 78 && bloodPressureConvert <= 126) && (heartbeatConvert > 92)) {
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
        if ((bloodPressureConvert >= 81 && bloodPressureConvert <= 134) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 81) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 134) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 81 && bloodPressureConvert <= 134) && (heartbeatConvert < 56)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 81 && bloodPressureConvert <= 134) && (heartbeatConvert > 92)) {
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
        if ((bloodPressureConvert >= 81 && bloodPressureConvert <= 139) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 81) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 139) && (heartbeatConvert >= 56 && heartbeatConvert <= 92)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 81 && bloodPressureConvert <= 139) && (heartbeatConvert < 56)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 81 && bloodPressureConvert <= 139) && (heartbeatConvert > 92)) {
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
        if ((bloodPressureConvert >= 79 && bloodPressureConvert <= 146) && (heartbeatConvert >= 56 && heartbeatConvert <= 93)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if((bloodPressureConvert < 79) && (heartbeatConvert >= 56 && heartbeatConvert <= 93)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsShort(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert > 146) && (heartbeatConvert >= 56 && heartbeatConvert <= 93)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.bloodPressureIsHigh(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 79 && bloodPressureConvert <= 146) && (heartbeatConvert < 56)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatFast(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else if ((bloodPressureConvert >= 79 && bloodPressureConvert <= 146) && (heartbeatConvert > 93)) {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heartbeatSlow(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        } else {
            Users_Health rs = usersHealthRepositories.save(usersHealthMapper.toUsersHealthEntity(healthsStatusRes.heathIsNotGood(), usersMapper.toUserDto(users), healthDto));
            return usersHealthMapper.toUsersHealthResponse(rs);
        }
    }
}

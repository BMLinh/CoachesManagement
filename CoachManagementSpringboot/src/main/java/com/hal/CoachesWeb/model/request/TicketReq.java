package com.hal.CoachesWeb.model.request;

import com.hal.CoachesWeb.entity.Ticket;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TicketReq {

    @NotNull
    @Min(value = 1, message = "Số lượng ghế phải lớn hơn 1")
    private int seatNum;
    private Ticket ticket;

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

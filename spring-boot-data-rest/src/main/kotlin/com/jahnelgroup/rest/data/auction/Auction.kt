package com.jahnelgroup.rest.data.auction

import com.jahnelgroup.rest.common.data.AbstractEntity
import com.jahnelgroup.rest.data.auction.bid.Bid
import com.jahnelgroup.rest.data.user.User
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@Entity
@EntityListeners(value = AuditingEntityListener::class)
data class Auction (

        @field:Embedded
        var auctionDetails : AuctionDetails = AuctionDetails(),

        var title : String = "",

        var status : Status = Status.OPEN

) : AbstractEntity() {

    @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var bids: Set<Bid> = emptySet()

    @ManyToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinColumn(name = "createdBy", insertable = false, updatable = false)
    var seller : User? = null

    enum class Status {
        OPEN, ENDED, CANCELED
    }

    fun isAcceptingBids(): Boolean {
        return Status.OPEN == this.status
    }

}
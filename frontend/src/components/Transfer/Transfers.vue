<template>
  <div>
    <div class="alert alert-danger alert-dismissible fade show" role="alert" v-if="alert.isOpen">
      {{ alert.msg }}
      <button type="button" class="close" @click="alert.isOpen = false">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <p>Total Transfers : {{ total }}</p>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Origin Account</th>
          <th scope="col">Destination Account</th>
          <th scope="col">Transfer Date</th>
          <th scope="col">Scheduled Date</th>
          <th scope="col">Tax</th>
          <th scope="col">Amount</th>
<th scope="col">Total</th>
        </tr>
        
      </thead>
      <tbody>
        <tr v-for="transfer in transfers" :key="transfer.id">
          <th scope="row">{{ transfer.id }}</th>
          <td>{{ transfer.originAccount }}</td>
          <td>{{ transfer.destinationAccount }}</td>
          <td>{{ format_date(transfer.transferDate) }}</td>
          <td>{{ format_date(transfer.scheduleDate) }}</td>
          <td>{{ transfer.tax }}</td>
          <td>{{ transfer.amount | toCurrency }}</td>

          <td>{{ (transfer.amount * (transfer.tax / 100)) + transfer.amount | toCurrency}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>
import { EventBus } from "./../../event-bus";
import moment from 'moment'

export default {
  name: "Transfers",
  data() {
    return {
      alert: {
        isOpen: false,
        msg: ""
      },
      transfers: [],
      total: 0
    };
  },
  methods: {
      format_date(value){
         if (value) {
           return moment(String(value)).format('DD/MM/YYYY')
          }
      },
    editUser(id) {
      this.$router.push("/" + id + "/edit");
    },
    removeUser(id) {
      this.$http.delete("delete?id=" + id).then(
        response => {
          console.log(response);
          this.users = this.users.filter(value => {
            return value.id !== id;
          });
          this.total--;
        },
        () => {
          this.alert.isOpen = true;
          this.alert.msg = "User couldn't delete!";
        }
      );
    }
  },
  created() {
    this.$http.get("list").then(
      response => {
        this.transfers = response.body.data;
        this.total = response.data.totalCount;

      },
      () => {
        this.alert.isOpen = true;
        this.alert.msg = "Transfers couldn't fetch from server!";
      }
    );

    EventBus.$on("userIsCreated", data => {
      if (data) {
        /* if the data has id field so can be push into users array
      otherwise there is an error occur*/
        if (data.id) {
          this.transfers.push(data);
          this.total++;
          if (this.alert.isOpen) this.alert.isOpen = false;
        } else {
          this.alert.isOpen = true;
          this.alert.msg = "The Transfer couldn't create! " + data;
        }
      } else {
        this.alert.isOpen = true;
        this.alert.msg = "Couldn't get any response from the server!";
      }
    });

    EventBus.$on("userIsUpdated", data => {
      if (data.id) {
        /* find the updated user according to id 
        and update the user in the users array */
        var userIndex = this.users.findIndex(user => user.id == data.id);
        if (userIndex != -1) this.$set(this.users, userIndex, data);
      } else {
        this.alert.isOpen = true;
        this.alert.msg = "The User couldn't update! " + data;
      }
    });
  }
};
</script>

<style scoped>
#action-button {
  margin: 0px 2% 0px 2%;
}
</style>



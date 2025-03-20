<template>
  <div>
    <h2>Schedule</h2>
    <div id="form">
      <div class="form-row">
        <div class="col">
Transfer Date : <VueDatePicker
    v-model="AccountTransfer.scheduleDate"
    format="DD-MM-YYYY"
  />
 </div>
         <div class="col">
 Origin Account :          <input
            id="origin"
            type="text"
            class="form-control"
            placeholder="origin account"
            v-model.trim="AccountTransfer.originAccount"
          >

        </div>
        <div class="col">
Destination Account :           <input
            id="destination"
            type="text"
            class="form-control"
            placeholder="Destinantion Account"
            v-model.trim="AccountTransfer.destinationAccount"
          >

        </div>
        <div class="col">
Amount :           <input
            id="amount"
            type="number"
            class="form-control"
            placeholder="Amount"
            v-model.trim="AccountTransfer.amount"
          >
        </div>
      </div>
      <br>
      <button class="btn btn-primary" @click="createTransfer">Schedule</button>
    </div>
  </div>
</template>

<script>
import { EventBus } from "./../../event-bus";
import { VueDatePicker } from '@mathieustan/vue-datepicker';
import '@mathieustan/vue-datepicker/dist/vue-datepicker.min.css';

export default {
  name: "TransferCreate",
  data() {
    return {
      scheduleDate: new Date(),
      AccountTransfer: {
        id: this.$route.params.id,
        scheduleDate: null,
        originAccount: null,
        destinationAccount: null,
        amount: 0
        
      }
    };
  },
    components : {
    VueDatePicker,
  },
  methods: {
    resetInputs() {
      this.AccountTransfer.scheduleDate = null;
      this.AccountTransfer.originAccount = null;
      this.AccountTransfer.destinationAccount = null;
      this.AccountTransfer.amount = 0;
    },
    createTransfer() {
      this.$http.post("save", this.AccountTransfer).then(
        response => {
          EventBus.$emit("transferIsCreated", response.body);
        },
        error => {
          EventBus.$emit("transferIsCreated", error.body.errorCode);
        }
      );
      this.resetInputs();
    }
  }
};
</script>

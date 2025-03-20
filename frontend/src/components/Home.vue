<template>
    <div>
        <h2>Description</h2>
        <div>
            <p>
                This project is a Account transfer schedule application. It transfers ammount from an account from anoyher within a tax amount by date from api server. You can apply operations and see the results. The Frontend side uses VueJs framework. There are some VueJs libraries that facilitate
                frontend process is used.
            </p>
    <table class="table" style="width:50%; text-align: center">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">fromTransferDay</th>
          <th scope="col">untilTransferDay</th>
          <th scope="col">Tax</th>
          <th scope="col">Amount</th>
        </tr>
        
      </thead>
      <tbody>
        <tr v-for="tax in taxes" :key="tax.id">
          <th scope="row">{{ tax.id }}</th>
          <td>{{ tax.fromTransferDay }}</td>
          <td>{{ tax.untilTransferDay }}</td>
          <td>{{ tax.tax }}</td>
          <td>{{ tax.ammount }}</td>
        </tr>
      </tbody>
    </table>
        </div>
    </div>
</template>


<script>

export default {
     name: "Home",
  data() {
    return {
      taxEndpoint: 'http://localhost:8082/api/taxExchange/list',
      taxes: [],
      total: 0
    };
  },


  created() {
    this.$http.get(this.taxEndpoint).then(
      response => {
        this.taxes = response.body.data;
        this.total = response.data.totalCount;

      }
    );
console.log("taxes" + this.taxes)

  }
}

</script>


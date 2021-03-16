<template>
  <div class="update-brewery">
    <div class="transbox" id="brewery-update-container">
      <form class="form-update-brewery" @submit.prevent="updateBrewery">
        <h3 class="h3 mb-3 font-weight-normal">Update Brewery</h3>
        <table id="update-brewery-table">
          <tr>
            <td class="left">
              <label for="name" class="sr-only">Name </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="name"
                class="form-control"
                placeholder="Name"
                v-model="brewery.name"
                required
                autofocus
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="address" class="sr-only">Address </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="address"
                class="form-control"
                placeholder="Address"
                v-model="brewery.address"
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="city" class="sr-only">City </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="city"
                class="form-control"
                placeholder="City"
                v-model="brewery.city"
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="zipcode" class="sr-only">ZIP Code </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="xipcode"
                class="form-control"
                v-model="brewery.zipCode"
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="phone-number" class="sr-only">Phone Number </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="phone-number"
                class="form-control"
                placeholder="(___) ____-____"
                v-model="brewery.phoneNumber"
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="history" class="sr-only">History/Description </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="history"
                class="form-control"
                placeholder="History"
                v-model="brewery.history"
              />
            </td>
          </tr>
          <tr>
            <td class="left">
              <label for="brewery-days" class="sr-only"
                >Days of Operation
              </label>
            </td>
            <td class="right">
              <div class="brewery-days">
                <table>
                  <th>Days</th>
                  <th>Open</th>
                  <th>Close</th>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        name="monday"
                        id="monday"
                        v-model="monday"
                      />
                      <label for="monday">Monday</label>
                    </td>
                    <td>
                      <input
                        type="time"
                        name="monday-open"
                        id="monday-open"
                        v-model="mondayOpen"
                        v-bind:disabled="monday === false"
                      />
                    </td>
                    <td>
                      <input
                        type="time"
                        name="monday-close"
                        id="monday-close"
                        v-model="mondayClose"
                        v-bind:disabled="monday === false"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        name="tuesday"
                        id="tuesday"
                        v-model="tuesday"
                      />
                      <label for="tuesday">Tuesday</label>
                    </td>
                    <td>
                      <input
                        type="time"
                        name="tuesday-open"
                        id="tuesday-open"
                        v-model="tuesdayOpen"
                        v-bind:disabled="tuesday === false"
                      />
                    </td>
                    <td>
                      <input
                        type="time"
                        name="tuesday-close"
                        id="tuesday-close"
                        v-model="tuesdayClose"
                        v-bind:disabled="tuesday === false"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        name="wednesday"
                        id="wednesday"
                        v-model="wednesday"
                      />
                      <label for="wednesday">Wednesday</label>
                    </td>
                    <td>
                      <input
                        type="time"
                        name="wednesday-open"
                        id="wednesday-open"
                        v-model="wednesdayOpen"
                        v-bind:disabled="wednesday === false"
                      />
                    </td>
                    <td>
                      <input
                        type="time"
                        name="wednesday-close"
                        id="wednesday-close"
                        v-model="wednesdayClose"
                        v-bind:disabled="wednesday === false"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        name="thursday"
                        id="thursday"
                        v-model="thursday"
                      />
                      <label for="thursday">Thursday</label>
                    </td>
                    <td>
                      <input
                        type="time"
                        name="thursday-open"
                        id="thursday-open"
                        v-model="thursdayOpen"
                        v-bind:disabled="thursday === false"
                      />
                    </td>
                    <td>
                      <input
                        type="time"
                        name="thursday-close"
                        id="thursday-close"
                        v-model="thursdayClose"
                        v-bind:disabled="thursday === false"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        name="friday"
                        id="friday"
                        v-model="friday"
                      />
                      <label for="friday">Friday</label>
                    </td>
                    <td>
                      <input
                        type="time"
                        name="friday-open"
                        id="friday-open"
                        v-model="fridayOpen"
                        v-bind:disabled="friday === false"
                      />
                    </td>
                    <td>
                      <input
                        type="time"
                        name="friday-close"
                        id="friday-close"
                        v-model="fridayClose"
                        v-bind:disabled="friday === false"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        name="saturday"
                        id="saturday"
                        v-model="saturday"
                      />
                      <label for="saturday">Saturday</label>
                    </td>
                    <td>
                      <input
                        type="time"
                        name="saturday-open"
                        id="saturday-open"
                        v-model="saturdayOpen"
                        v-bind:disabled="saturday === false"
                      />
                    </td>
                    <td>
                      <input
                        type="time"
                        name="saturday-close"
                        id="saturday-close"
                        v-model="saturdayClose"
                        v-bind:disabled="saturday === false"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        name="sunday"
                        id="sunday"
                        v-model="sunday"
                      />
                      <label for="sunday">Sunday</label>
                    </td>
                    <td>
                      <input
                        type="time"
                        name="sunday-open"
                        id="sunday-open"
                        v-model="sundayOpen"
                        v-bind:disabled="sunday === false"
                      />
                    </td>
                    <td>
                      <input
                        type="time"
                        name="sunday-close"
                        id="sunday-close"
                        v-model="sundayClose"
                        v-bind:disabled="sunday === false"
                      />
                    </td>
                  </tr>
                </table>
              </div>
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="atmosphere" class="sr-only">Atmosphere </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="atmosphere"
                class="form-control"
                placeholder="Atmosphere"
                v-model="brewery.atmosphere"
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="family-friendly" class="sr-only"
                >Family Friendly
              </label>
            </td>
            <td class="right">
              <input
                type="checkbox"
                id="family-friendly"
                class="form-control"
                v-model="brewery.familyFriendly"
              />
            </td>
          </tr>
          <tr>
            <td class="left">
              <label for="patio" class="sr-only">Patio </label>
            </td>
            <td class="right">
              <input
                type="checkbox"
                id="patio"
                class="form-control"
                v-model="brewery.patio"
              />
            </td>
          </tr>
          <tr>
            <td class="left">
              <label for="food" class="sr-only">Food </label>
            </td>
            <td class="right">
              <input
                type="checkbox"
                id="food"
                class="form-control"
                v-model="brewery.food"
              />
            </td>
          </tr>
          <tr>
            <td class="left">
              <label for="is-active" class="sr-only">Active </label>
            </td>
            <td class="right">
              <input
                type="checkbox"
                id="is-active"
                class="form-control"
                v-model="brewery.active"
              />
            </td>
          </tr>
        </table>

        <br />
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Save
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";

export default {
  data() {
    return {
      name: "update-brewery",
      monday: false,
      tuesday: false,
      wednesday: false,
      thursday: false,
      friday: false,
      saturday: false,
      sunday: false,
      mondayOpen: "",
      mondayClose: "",
      tuesdayOpen: "",
      tuesdayClose: "",
      wednesdayOpen: "",
      wednesdayClose: "",
      thursdayOpen: "",
      thursdayClose: "",
      fridayOpen: "",
      fridayClose: "",
      saturdayOpen: "",
      saturdayClose: "",
      sundayOpen: "",
      sundayClose: "",
    };
  },
  computed: {
    brewery() {
      return this.$store.state.currentBrewery;
    },
  },
  created() {
    this.getDays();
  },
  methods: {
    updateBrewery() {
      this.setHours();
      breweryService.update(this.brewery).then((response) => {
        this.$store.commit("SET_CURRENT_BREWERY", response.data);
        this.$emit("hideForm");
      });
    },
    setHours() {
      let days = [];
      let hours = [];
      if (this.monday) {
        days.push("Monday");
        hours.push(this.mondayOpen + "-" + this.mondayClose);
      }
      if (this.tuesday) {
        days.push("Tuesday");
        hours.push(this.tuesdayOpen + "-" + this.tuesdayClose);
      }
      if (this.wednesday) {
        days.push("Wednesday");
        hours.push(this.wednesdayOpen + "-" + this.wednesdayClose);
      }
      if (this.thursday) {
        days.push("Thursday");
        hours.push(this.thursdayOpen + "-" + this.thursdayClose);
      }
      if (this.friday) {
        days.push("Friday");
        hours.push(this.fridayOpen + "-" + this.fridayClose);
      }
      if (this.saturday) {
        days.push("Saturday");
        hours.push(this.saturdayOpen + "-" + this.saturdayClose);
      }
      if (this.sunday) {
        days.push("Sunday");
        hours.push(this.sundayOpen + "-" + this.sundayClose);
      }
      this.brewery.daysOpen = days;
      this.brewery.hours = hours;
    },
    getDays() {
      for (let i = 0; i < this.brewery.daysOpen.length; i++) {
          const hours = this.brewery.hours[i].trim().split("-");
        if (this.brewery.daysOpen[i].toLowerCase().includes("mon")) {
          this.monday = true;
          this.mondayOpen = hours[0];
          this.mondayClose = hours[1];
        }
        if (this.brewery.daysOpen[i].toLowerCase().includes("tue")) {
          this.tuesday = true;
          this.tuesdayOpen = hours[0];
          this.tuesdayClose = hours[1];
        }
        if (this.brewery.daysOpen[i].toLowerCase().includes("wed")) {
          this.wednesday = true;
          this.wednesdayOpen = hours[0];
          this.wednesdayClose = hours[1];
        }
        if (this.brewery.daysOpen[i].toLowerCase().includes("thu")) {
          this.thursday = true;
          this.thursdayOpen = hours[0];
          this.thursdayClose = hours[1];
        }
        if (this.brewery.daysOpen[i].toLowerCase().includes("fri")) {
          this.friday = true;
          this.fridayOpen = hours[0];
          this.fridayClose = hours[1];
        }
        if (this.brewery.daysOpen[i].toLowerCase().includes("sat")) {
          this.saturday = true;
          this.saturdayOpen = hours[0];
          this.saturdayClose = hours[1];
        }
        if (this.brewery.daysOpen[i].toLowerCase().includes("sun")) {
          this.sunday = true;
          this.sundayOpen = hours[0];
          this.sundayClose = hours[1];
        }
      }
    },
  },
};
</script>

<style></style>

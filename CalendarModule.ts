import { NativeModules } from 'react-native';

const { CalendarModule } = NativeModules;

interface CalendarInterface {
  createCalendarEvent(name: string, location: string): void;
  eventReminder(): void,
}

export default CalendarModule as CalendarInterface;
